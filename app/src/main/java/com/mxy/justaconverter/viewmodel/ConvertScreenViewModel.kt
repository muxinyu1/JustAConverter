package com.mxy.justaconverter.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel

class ConvertScreenViewModel(
    var chooseFileType: ScaffoldContentViewModel.ChooseFileType,
    var from: String,
    var to: String,
    var filePath: Uri?,
    var convertButtonState: Boolean = true
) : ViewModel() {
    val convert: () -> Unit = {
        //仅用于测试：
        Log.d("mxy!!!", "开始转换！\n文件名：${filePath?.path}\n从${from}到${to}")
        //TODO:实现文件转换代码
    }
}