package com.mxy.justaconverter

import com.cloudconvert.client.CloudConvertClient
import com.cloudconvert.client.setttings.StringSettingsProvider
import com.cloudconvert.dto.request.UploadImportRequest
import com.convertapi.client.Config
import com.convertapi.client.ConvertApi
import com.convertapi.client.Param
import com.mxy.justaconverter.util.Utility
import com.mxy.justaconverter.util.Utility.Companion.getResponseUrl
import com.mxy.justaconverter.util.Utility.Companion.getSourceFileUrl
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.mime.MultipartEntityBuilder
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import org.json.JSONException
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test
import java.io.File
import java.io.FileInputStream
import java.nio.file.Paths


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun cloudConvertTest() {
        val streamAndUrl = Utility.getDownloadUrlFromFile(null, "F:\\video.mp4", "mp4", "flv", "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiY2ZlNWRiODk5NzFjMjNmYzc3ZjE0Y2I0MWIyYzU4YWYxMzJhOGVlZDE3ZmZmNGZkNDJmMDMyMDIxNTMyZDU3ODc5YjRlNGRjMDdiZGMxNTYiLCJpYXQiOjE2NTA4OTEyMzYuODgwNDMsIm5iZiI6MTY1MDg5MTIzNi44ODA0MzIsImV4cCI6NDgwNjU2NDgzNi44NzY5MjEsInN1YiI6IjU1NTc1MTg3Iiwic2NvcGVzIjpbInVzZXIucmVhZCIsInVzZXIud3JpdGUiLCJ0YXNrLnJlYWQiLCJ0YXNrLndyaXRlIiwid2ViaG9vay5yZWFkIiwid2ViaG9vay53cml0ZSIsInByZXNldC5yZWFkIiwicHJlc2V0LndyaXRlIl19.MppWAzx-th_k-ABxoCKzY8uXD0vT1xDYq_49XHCbadEaQNZzSfD3wtHG5duCPqx-Se7kdtbuw5ePhYsbpEikt5_4p-cFbgk7Q-Bm03k4uL45Hw_oMEQwePkYpnsRuYsX6CuEhUzr436uG0stiS1s1C07PySj4Ffej4ZGK8fBrTcuOEjL6rGepmcyvfRqkumh0dkO4ZQRsaQHr7hkqGdvCMonSa1dtZOF7huU0T4hX53uxVBrfdpDBzHkWYhgtusQ2a08KtXVqWm8ZXOQ5J4a_eTDd_pVvmQpfFHEWDRSds0-h-Bjrt7Zg2T8kNLZwLGvQ8Spt2Amuh3SmvbRpg27DM0h2hHwWoDZZmZUTO1dBHIsrO5IbbZol2B2FGh9ZCvLLgy08ioUblmgwuqqR8_umHjRhhDzMnzfRMRqXsYAK09VC_6CiYxwHKT_FcY6H5TH1WKsdzcCXFUPp56RDjGR49dPaTtY3bYOriPGSiQkTfYqEfmusF965Kxp7mp1JzSddQqV-yhVkm46qeUQkR-NOAS82EN6_siCM0UrZB0ftHFvctUkeVLpNzk9vaIom09uOE-kjpqHfBARXPRu_KEhlwjgKD57qeX3T-f5Cv1wdyUonzLZoePzQEupP3TcChZVSVAZXygWlQ_6fj83plX9OgReAsgj8cJbltnwk0ujvao")
        println("转换后文件下载链接：$streamAndUrl")
        //FileUtils.copyInputStreamToFile(streamAndUrl.second, File("F:\\res.png"))
    }

    @Test
    fun uploadFileCloudConvertTest() {
        val client = CloudConvertClient(
            StringSettingsProvider(
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiY2ZlNWRiODk5NzFj" +
                        "MjNmYzc3ZjE0Y2I0MWIyYzU4YWYxMzJhOGVlZDE3ZmZmNGZkNDJmMDMyMDIxNTMyZDU3ODc5YjRl" +
                        "NGRjMDdiZGMxNTYiLCJpYXQiOjE2NTA4OTEyMzYuODgwNDMsIm5iZiI6MTY1MDg5MTIzNi44ODA0Mz" +
                        "IsImV4cCI6NDgwNjU2NDgzNi44NzY5MjEsInN1YiI6IjU1NTc1MTg3Iiwic2NvcGVzIjpbInVzZX" +
                        "IucmVhZCIsInVzZXIud3JpdGUiLCJ0YXNrLnJlYWQiLCJ0YXNrLndyaXRlIiwid2ViaG9vay5yZ" +
                        "WFkIiwid2ViaG9vay53cml0ZSIsInByZXNldC5yZWFkIiwicHJlc2V0LndyaXRlIl19.MppWAzx-" +
                        "th_k-ABxoCKzY8uXD0vT1xDYq_49XHCbadEaQNZzSfD3wtHG5duCPqx-Se7kdtbuw5ePhYsbpEi" +
                        "kt5_4p-cFbgk7Q-Bm03k4uL45Hw_oMEQwePkYpnsRuYsX6CuEhUzr436uG0stiS1s1C07PySj4Ff" +
                        "ej4ZGK8fBrTcuOEjL6rGepmcyvfRqkumh0dkO4ZQRsaQHr7hkqGdvCMonSa1dtZOF7huU0T4hX53u" +
                        "xVBrfdpDBzHkWYhgtusQ2a08KtXVqWm8ZXOQ5J4a_eTDd_pVvmQpfFHEWDRSds0-h-Bjrt7Zg2T8kN" +
                        "LZwLGvQ8Spt2Amuh3SmvbRpg27DM0h2hHwWoDZZmZUTO1dBHIsrO5IbbZol2B2FGh9ZCvLLgy08ioU" +
                        "blmgwuqqR8_umHjRhhDzMnzfRMRqXsYAK09VC_6CiYxwHKT_FcY6H5TH1WKsdzcCXFUPp56RDjGR49" +
                        "dPaTtY3bYOriPGSiQkTfYqEfmusF965Kxp7mp1JzSddQqV-yhVkm46qeUQkR-NOAS82EN6_siCM0Ur" +
                        "ZB0ftHFvctUkeVLpNzk9vaIom09uOE-kjpqHfBARXPRu_KEhlwjgKD57qeX3T-f5Cv1wdyUonzLZo" +
                        "ePzQEupP3TcChZVSVAZXygWlQ_6fj83plX9OgReAsgj8cJbltnwk0ujvao",
                "NULL",
                false
            )
        )
        val inputStream = FileInputStream(File("F:\\res.png"))
        val uploadResponse =
            inputStream.let {
                client.importUsing().upload(
                    UploadImportRequest(),
                    it, "res.png"
                ).body
            }
        assert(uploadResponse != null)
        val waitUpload = uploadResponse?.let { client.tasks().wait(it.id) }?.body
        assert(waitUpload != null)

    }

    @Test
    fun convertApiTest() {
        Config.setDefaultSecret("GiMaAdmQ47i5HNlF")
        ConvertApi.convert(
            "wav", "mp3",
            Param("File", Paths.get("F:\\music.wav"))
        ).get().saveFilesSync(Paths.get("F:\\result.mp3"))
    }

    @Test
    fun anonFilesTest() {
        val client = OkHttpClient.Builder().build()
        var builder: HttpUrl.Builder? = null;
        try {
            val httpUrl = "https://api.anonfiles.com/upload".toHttpUrlOrNull()
            if (httpUrl != null) {
                builder = httpUrl.newBuilder()
            }
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
        if (builder == null) {
            return
        }
        val mediaType = "application/json;charset=UTF-8".toMediaTypeOrNull()
        val jsonObject = JSONObject()
        try {
            jsonObject.put("file", "F:\\music.wav")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val requestBody = jsonObject.toString().toRequestBody(mediaType)
        val request = Request.Builder().url(builder.build()).post(requestBody).build()

    }

    @Test
    fun postFiles() {
        val file = File("F:\\mxy.pptx")
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
                val import = getSourceFileUrl(downloadUrl)
                println(import)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
