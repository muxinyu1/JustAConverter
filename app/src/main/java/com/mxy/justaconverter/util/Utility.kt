package com.mxy.justaconverter.util

import android.content.Context
import com.cloudconvert.client.CloudConvertClient
import com.cloudconvert.client.setttings.StringSettingsProvider
import com.cloudconvert.dto.request.ConvertFilesTaskRequest
import com.cloudconvert.dto.request.UrlExportRequest
import com.cloudconvert.dto.request.UrlImportRequest
import com.google.common.collect.ImmutableMap
import com.google.gson.Gson
import com.mxy.justaconverter.R
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.mime.MultipartEntityBuilder
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import org.jsoup.Jsoup
import java.io.File
import java.net.URL

class Utility {
    companion object {
        fun getDownloadUrlFromFile(
            context: Context?,
            filePath: String,
            from: String,
            to: String,
            testApiKey: String = ""
        ): String? {
            //TODO:此处的API_KEY应该从数据库中读取
            val client = CloudConvertClient(context?.getString(R.string.api_key)?.let {
                StringSettingsProvider(
                    //TODO:在应用里运行时使用context.stringRes
                    it,
                    "NULL",
                    false
                )
            })
            //Log.d("mxy!!!", "client创建成功")
            val importUrl = getHttpsFromLocalFile(filePath)
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
            //Log.d("mxy!!!", "Job创建成功")
            val waitJobResponse = client.jobs().wait(createJobResponse?.id as String).body
            val exportUrlTask = waitJobResponse?.tasks?.stream()?.filter {
                it.name == "export-my-file"
            }?.findFirst()?.get()
            return exportUrlTask?.result?.files?.get(0)?.get("url")
        }

        fun getResponseUrl(json: String): String {
            val gson = Gson()
            return (((((gson.fromJson(
                json,
                Map::class.java
            )["data"] as Map<*, *>)["file"]) as Map<*, *>)["url"]) as Map<*, *>)["full"] as String
        }

        fun getSourceFileUrl(url: String?): String? {
            val document = Jsoup.parse(URL(url), 10_000)
            val res = document.getElementById("download-url")?.attr("href")
            println("源文件下载链接：$res")
            return res
        }

        private fun getHttpsFromLocalFile(filePath: String): String? {
            val file = File(filePath)
            try {
                if (file.exists()) {
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
                    //println(downloadUrl)
                    return getSourceFileUrl(downloadUrl)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
    }
}