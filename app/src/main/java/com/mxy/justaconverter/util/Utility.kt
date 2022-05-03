package com.mxy.justaconverter.util

import android.content.Context
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
            context: Context,
            filePath: String,
            from: String,
            to: String
        ): Pair<String?, @Nullable InputStream?> {
            //TODO:此处的API_KEY应该从数据库中读取
            val client = CloudConvertClient(
                StringSettingsProvider(
                    context.getString(R.string.api_key),
                    "NULL",
                    false
                )
            )
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