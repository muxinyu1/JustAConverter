package com.mxy.justaconverter.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mxy.justaconverter.R
import com.mxy.justaconverter.util.Utility
import kotlinx.coroutines.*

class ConvertScreenViewModel(
    var chooseFileType: ScaffoldContentViewModel.ChooseFileType,
    var from: String,
    var to: String,
    var filePath: Uri?,
    var convertButtonState: Boolean = true,
    context: Context
) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.Q)
    val convert: () -> Unit = {
        //仅用于测试：
        Log.d("mxy!!!", "开始转换！\n文件名：${filePath?.path}\n从${from}到${to}")
        Log.d("mxy!!!", "选择的文件转换类型：${chooseFileType.name}")
        if (filePath == null) {
            Toast.makeText(
                context,
                context.getString(R.string.converter_screen_plz_choose_file),
                Toast.LENGTH_SHORT
            )
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                if (filePath == null) {
                    Log.d("mxy!!!", "filepath空!")
                }
                else {
                    val downloadUrl = Utility.getDownloadUrlFromFileOkHttp(context, filePath!!, from, to)
                    if (downloadUrl == null) {
                        Log.d("mxy!!!", "downloadUrl 是空的！")
                    }
                    else {
                        Log.d("mxy!!!", downloadUrl)
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(downloadUrl)))
                    }
                }
            }
        }
        //Log.d("mxy!!!", "转换结果的Url：${streamAndUrl.second}")
    }
    val onFilePathChanged: (Uri?) -> Unit = {
        filePath = it
    }
    val onFromFormatChanged: (String) -> Unit = {
        from = it
    }
    val onToFormatChanged: (String) -> Unit = {
        to = it
    }
}