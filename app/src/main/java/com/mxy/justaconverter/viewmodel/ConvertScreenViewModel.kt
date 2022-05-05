package com.mxy.justaconverter.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.mxy.justaconverter.util.Utility
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.runBlocking

class ConvertScreenViewModel(
    var chooseFileType: ScaffoldContentViewModel.ChooseFileType,
    var from: String,
    var to: String,
    var filePath: Uri?,
    var convertButtonState: Boolean = true,
    context: Context
) : ViewModel() {
    val convert: () -> Unit = {
        //仅用于测试：
        Log.d("mxy!!!", "开始转换！\n文件名：${filePath?.path}\n从${from}到${to}")
        Log.d("mxy!!!", "选择的文件转换类型：${chooseFileType.name}")
        //TODO:异步方法实现文件转换
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