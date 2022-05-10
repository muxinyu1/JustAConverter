package com.mxy.justaconverter.util

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.FileUtils.copy
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.annotation.RequiresApi
import com.cloudconvert.client.CloudConvertClient
import com.cloudconvert.client.setttings.StringSettingsProvider
import com.cloudconvert.dto.request.ConvertFilesTaskRequest
import com.cloudconvert.dto.request.UrlExportRequest
import com.cloudconvert.dto.request.UrlImportRequest
import com.google.common.collect.ImmutableMap
import com.google.gson.Gson
import com.mxy.justaconverter.R
import com.mxy.justaconverter.jsons.anonfiles.AnonFilesDownloadResponse
import com.mxy.justaconverter.jsons.cloudconvert.download.CloudConvertDownloadResponse
import com.mxy.justaconverter.jsons.cloudconvert.id.CloudConvertIdResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.mime.MultipartEntityBuilder
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import org.jsoup.Jsoup
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Utility {
    companion object {

        private val client by lazy {
            OkHttpClient.Builder().connectTimeout(6000, TimeUnit.SECONDS)
                .readTimeout(6000, TimeUnit.SECONDS)
                .writeTimeout(6000, TimeUnit.SECONDS).build()
        }

        private val gson by lazy {
            Gson()
        }

        @RequiresApi(Build.VERSION_CODES.Q)
        fun getDownloadUrlFromFile(
            context: Context?,
            filePath: Uri,
            from: String,
            to: String,
            testApiKey: String = "",
        ): String? {
            //TODO:此处的API_KEY应该从数据库中读取
            val client = CloudConvertClient(
                context?.let {
                    StringSettingsProvider(
                        //TODO:在应用里运行时使用context.stringRes
                        it.getString(R.string.api_key),
                        "NULL",
                        false
                    )
                }
            )
            Log.d("mxy!!!", "client创建成功")
            val importUrl = getHttpsFromLocalFileOkHttp(filePath, context!!, from)
            val createJobResponse = client.jobs().create(
                ImmutableMap.of(
                    "import-my-file",
                    UrlImportRequest().setUrl(importUrl),
                    "convert-my-file",
                    ConvertFilesTaskRequest().setInput("import-my-file")
                        .setInputFormat(from.lowercase()).setOutputFormat(to.lowercase()),
                    "export-my-file",
                    UrlExportRequest().setInput("convert-my-file")
                )
            ).body
            assert(createJobResponse != null)
            Log.d("mxy!!!", "Job创建成功")
            val waitJobResponse = client.jobs().wait(createJobResponse?.id as String).body
            val exportUrlTask = waitJobResponse?.tasks?.stream()?.filter {
                it.name == "export-my-file"
            }?.findFirst()?.get()
            return exportUrlTask?.result?.files?.get(0)?.get("url")
        }

        fun getResponseUrl(json: String): String {
            val anonFilesDownloadResponse = gson.fromJson(json, AnonFilesDownloadResponse::class.java)
            return anonFilesDownloadResponse.data.file.url.full
        }

        fun getSourceFileUrl(url: String?): String? {
            Log.d("mxy!!!", "网页链接：$url")
            val document = Jsoup.parse(URL(url), 10_000)
            val res = document.getElementById("download-url")?.attr("href")
            Log.d("mxy!!!", "源文件下载链接：$res")
            return res
        }

        fun getHttpsFromLocalFile(filePath: Uri, context: Context?): String? {
            val file = context?.contentResolver?.openInputStream(filePath)
            if (file != null) {
                Log.d("mxy!!!", "文件存在")
            } else {
                Log.d("mxy!!!", "草，原来文件一直不存在")
            }
            try {
                if (file != null) {
                    val client = HttpClientBuilder.create().build()
                    val httpPost = HttpPost("https://api.anonfiles.com/upload")
                    httpPost.config = RequestConfig.custom().setConnectTimeout(200_000).build()
                    val multipartEntityBuilder = MultipartEntityBuilder.create()
                    multipartEntityBuilder.addBinaryBody("file", file)
                    httpPost.entity = multipartEntityBuilder.build()
                    val response = client.execute(httpPost)
                    val entity = response.entity
                    val jsonStr = EntityUtils.toString(entity)
                    //得到了json字符串，下面解析获取文件对应的html网址
                    val downloadUrl = getResponseUrl(jsonStr)
                    Log.d("mxy!!!", "downloadUrl = $downloadUrl")
                    return getSourceFileUrl(downloadUrl)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        @RequiresApi(Build.VERSION_CODES.Q)
        fun getHttpsFromLocalFileOkHttp(filePath: Uri, context: Context, from: String): String? {
            val inputStream = context.contentResolver.openInputStream(filePath)
            val tempFileDisplayName = "${System.currentTimeMillis()}${
                Random.nextInt(
                    0,
                    9999
                )
            }.${
                MimeTypeMap.getSingleton()
                    .getExtensionFromMimeType(context.contentResolver.getType(filePath))
            }"

            val file = File("${context.cacheDir.absolutePath}/$tempFileDisplayName").apply {
                val fileOutputStream = FileOutputStream(this)
                if (inputStream != null) {
                    copy(inputStream, fileOutputStream)
                    inputStream.close()
                    fileOutputStream.close()
                }
            }
            if (!file.exists()) {
                Log.d("mxy!!!", "草，文件又是不存在!")
                return null
            }
            val url = context.getString(R.string.anon_files_url)
            Log.d("mxy!!!", "客户端创建成功！")
            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    "file",
                    "file.${from.lowercase()}",
                    file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                )
                .build()
            Log.d("mxy!!!", "请求体创建成功！")
            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()
            Log.d("mxy!!!", "request创建成功！")
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseUrl = response.body?.string()!!
                //Log.d("mxy!!!", responseUrl)
                val downloadUrl = getResponseUrl(responseUrl)
                return getSourceFileUrl(downloadUrl)
            }
            return null
        }

        private fun getTaskIdFromResponse(json: String): String {
            val idResponse = gson.fromJson(json, CloudConvertIdResponse::class.java)
            return idResponse.data.id
        }

        @RequiresApi(Build.VERSION_CODES.Q)
        fun getDownloadUrlFromFileOkHttp(
            context: Context,
            filePath: Uri,
            from: String,
            to: String,
        ): String? {
            val importUrl = getHttpsFromLocalFileOkHttp(filePath, context, from)
            val postContent = "{" +
                    "\"tasks\":{" +
                    "\"import-my-file\":{" +
                    "\"operation\":\"import/url\"," +
                    "\"url\":\"$importUrl\"" +
                    "}," +
                    "\"convert-my-file\":{" +
                    "\"operation\":\"convert\"," +
                    "\"input\":\"import-my-file\"," +
                    "\"input_format\":\"${from.lowercase()}\"," +
                    "\"output_format\":\"${to.lowercase()}\"" +
                    "}," +
                    "\"export-my-file\":{" +
                    "\"operation\":\"export/url\"," +
                    "\"input\":\"convert-my-file\"" +
                    "}}}"
            val requestBody = postContent.toRequestBody("application/json".toMediaType())
            val request = Request.Builder()
                .url(context.getString(R.string.cloud_convert_create_jobs_url))
                .addHeader("Authorization", "Bearer ${context.getString(R.string.api_key)}")
                .addHeader("Content-type", "application/json")
                .post(requestBody)
                .build()
            Log.d("mxy!!!", "向cloudconvert的request构建成功！")
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                return null
            }
            val responseJson = response.body!!.string()
            Log.d("mxy!!!", "responseJson = $responseJson")
            val taskId = getTaskIdFromResponse(responseJson)
            Log.d("mxy!!!", "taskId = $taskId")
            val waitForJobRequest = Request.Builder()
                .addHeader("Authorization", "Bearer ${context.getString(R.string.api_key)}")
                .url("https://api.cloudconvert.com/v2/jobs/${taskId}/wait")
                .build()
            val waitForJobResponse = client.newCall(waitForJobRequest).execute()
            if (!waitForJobResponse.isSuccessful) {
                Log.d("mxy!!!", "waitForJobResponse不成功！")
                return null
            }
            val waitForJobResponseJson = waitForJobResponse.body!!.string()
            val downloadResponse =
                gson.fromJson(waitForJobResponseJson, CloudConvertDownloadResponse::class.java)
            val downloadUrl =
                downloadResponse.data.tasks.find {
                    it.name == "export-my-file"
                }?.result?.files?.get(0)?.url
            return downloadUrl
        }
    }
}