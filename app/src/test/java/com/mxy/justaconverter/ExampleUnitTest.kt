package com.mxy.justaconverter

import com.cloudconvert.client.CloudConvertClient
import com.cloudconvert.client.setttings.StringSettingsProvider
import com.cloudconvert.dto.request.UploadImportRequest
import com.mxy.justaconverter.util.Utility
import org.apache.commons.io.FileUtils
import org.junit.Test

import org.junit.Assert.*
import java.io.File

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
        val streamAndUrl = Utility.getResultStream(null, "F:\\res.jpg", "JPG", "PNG")
        print(streamAndUrl.first)
        //FileUtils.copyInputStreamToFile(streamAndUrl.second, File("F:\\res.png"))
    }

    @Test
    fun uploadFileCloudConvertTest() {
        val client = CloudConvertClient(
            StringSettingsProvider(
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiY2ZlNWRiODk5NzFjMjNmYzc3ZjE0Y2I0MWIyYzU4YWYxMzJhOGVlZDE3ZmZmNGZkNDJmMDMyMDIxNTMyZDU3ODc5YjRlNGRjMDdiZGMxNTYiLCJpYXQiOjE2NTA4OTEyMzYuODgwNDMsIm5iZiI6MTY1MDg5MTIzNi44ODA0MzIsImV4cCI6NDgwNjU2NDgzNi44NzY5MjEsInN1YiI6IjU1NTc1MTg3Iiwic2NvcGVzIjpbInVzZXIucmVhZCIsInVzZXIud3JpdGUiLCJ0YXNrLnJlYWQiLCJ0YXNrLndyaXRlIiwid2ViaG9vay5yZWFkIiwid2ViaG9vay53cml0ZSIsInByZXNldC5yZWFkIiwicHJlc2V0LndyaXRlIl19.MppWAzx-th_k-ABxoCKzY8uXD0vT1xDYq_49XHCbadEaQNZzSfD3wtHG5duCPqx-Se7kdtbuw5ePhYsbpEikt5_4p-cFbgk7Q-Bm03k4uL45Hw_oMEQwePkYpnsRuYsX6CuEhUzr436uG0stiS1s1C07PySj4Ffej4ZGK8fBrTcuOEjL6rGepmcyvfRqkumh0dkO4ZQRsaQHr7hkqGdvCMonSa1dtZOF7huU0T4hX53uxVBrfdpDBzHkWYhgtusQ2a08KtXVqWm8ZXOQ5J4a_eTDd_pVvmQpfFHEWDRSds0-h-Bjrt7Zg2T8kNLZwLGvQ8Spt2Amuh3SmvbRpg27DM0h2hHwWoDZZmZUTO1dBHIsrO5IbbZol2B2FGh9ZCvLLgy08ioUblmgwuqqR8_umHjRhhDzMnzfRMRqXsYAK09VC_6CiYxwHKT_FcY6H5TH1WKsdzcCXFUPp56RDjGR49dPaTtY3bYOriPGSiQkTfYqEfmusF965Kxp7mp1JzSddQqV-yhVkm46qeUQkR-NOAS82EN6_siCM0UrZB0ftHFvctUkeVLpNzk9vaIom09uOE-kjpqHfBARXPRu_KEhlwjgKD57qeX3T-f5Cv1wdyUonzLZoePzQEupP3TcChZVSVAZXygWlQ_6fj83plX9OgReAsgj8cJbltnwk0ujvao",
                "NULL",
                false
            )
        )
        val inputStream = javaClass.getResourceAsStream("F:\\res.png")
        assert(inputStream != null)
        val uploadResponse =
            inputStream?.let {
                client.importUsing().upload(UploadImportRequest(),
                    it, "res.png").body
            }
        assert(uploadResponse != null)
        val waitUpload = uploadResponse?.let { client.tasks().wait(it.id) }?.body
        assert(waitUpload != null)

    }
}
