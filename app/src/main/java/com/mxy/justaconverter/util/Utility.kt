package com.mxy.justaconverter.util

import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.cloudconvert.client.CloudConvertClient
import com.cloudconvert.client.setttings.StringSettingsProvider
import com.cloudconvert.dto.request.ConvertFilesTaskRequest
import com.cloudconvert.dto.request.UrlExportRequest
import com.cloudconvert.dto.request.UrlImportRequest
import com.google.common.collect.ImmutableMap
import com.mxy.justaconverter.R
import org.jetbrains.annotations.Nullable
import java.io.InputStream
import java.util.function.Predicate

class Utility {
    companion object {
        fun getResultStream(
            context: Context?,
            filePath: String,
            from: String,
            to: String
        ): Pair<String?, @Nullable InputStream?> {
            //TODO:此处的API_KEY应该从数据库中读取
            val client = CloudConvertClient(
                StringSettingsProvider(
                    //TODO:在应用里运行时使用context.stringRes
                    "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiY2ZlNWRiODk5NzFjMjNmYzc3ZjE0Y2I0MWIyYzU4YWYxMzJhOGVlZDE3ZmZmNGZkNDJmMDMyMDIxNTMyZDU3ODc5YjRlNGRjMDdiZGMxNTYiLCJpYXQiOjE2NTA4OTEyMzYuODgwNDMsIm5iZiI6MTY1MDg5MTIzNi44ODA0MzIsImV4cCI6NDgwNjU2NDgzNi44NzY5MjEsInN1YiI6IjU1NTc1MTg3Iiwic2NvcGVzIjpbInVzZXIucmVhZCIsInVzZXIud3JpdGUiLCJ0YXNrLnJlYWQiLCJ0YXNrLndyaXRlIiwid2ViaG9vay5yZWFkIiwid2ViaG9vay53cml0ZSIsInByZXNldC5yZWFkIiwicHJlc2V0LndyaXRlIl19.MppWAzx-th_k-ABxoCKzY8uXD0vT1xDYq_49XHCbadEaQNZzSfD3wtHG5duCPqx-Se7kdtbuw5ePhYsbpEikt5_4p-cFbgk7Q-Bm03k4uL45Hw_oMEQwePkYpnsRuYsX6CuEhUzr436uG0stiS1s1C07PySj4Ffej4ZGK8fBrTcuOEjL6rGepmcyvfRqkumh0dkO4ZQRsaQHr7hkqGdvCMonSa1dtZOF7huU0T4hX53uxVBrfdpDBzHkWYhgtusQ2a08KtXVqWm8ZXOQ5J4a_eTDd_pVvmQpfFHEWDRSds0-h-Bjrt7Zg2T8kNLZwLGvQ8Spt2Amuh3SmvbRpg27DM0h2hHwWoDZZmZUTO1dBHIsrO5IbbZol2B2FGh9ZCvLLgy08ioUblmgwuqqR8_umHjRhhDzMnzfRMRqXsYAK09VC_6CiYxwHKT_FcY6H5TH1WKsdzcCXFUPp56RDjGR49dPaTtY3bYOriPGSiQkTfYqEfmusF965Kxp7mp1JzSddQqV-yhVkm46qeUQkR-NOAS82EN6_siCM0UrZB0ftHFvctUkeVLpNzk9vaIom09uOE-kjpqHfBARXPRu_KEhlwjgKD57qeX3T-f5Cv1wdyUonzLZoePzQEupP3TcChZVSVAZXygWlQ_6fj83plX9OgReAsgj8cJbltnwk0ujvao",
                    "NULL",
                    false
                )
            )
            //Log.d("mxy!!!", "client创建成功")
            val createJobResponse = client.jobs().create(
                ImmutableMap.of(
                    "import-my-file",
                    UrlImportRequest().setUrl(filePath),
                    "convert-my-file",
                    ConvertFilesTaskRequest().setInput("import-my-file")
                        .setInputFormat(from.lowercase()).setOutputFormat(to.lowercase()),
                    "export-my-file",
                    UrlExportRequest().setInput("convert-my-file")
                )
            ).body
            //Log.d("mxy!!!", "Job创建成功")
            val waitJobResponse = createJobResponse?.let {
                client.jobs().wait(it.id).body
            }
            val exportUrlTask = waitJobResponse?.tasks?.stream()?.filter {
                it.name == "export-my-file"
            }?.findFirst()?.get()
            val exportUrl = exportUrlTask?.result?.files?.get(0)?.get("url")
            return Pair(exportUrl, exportUrl?.let { client.files().download(it).body })
        }
    }
}