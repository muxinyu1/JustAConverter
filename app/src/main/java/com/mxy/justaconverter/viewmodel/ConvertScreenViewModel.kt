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
import com.mxy.justaconverter.routing.JustAConverterRouter
import com.mxy.justaconverter.routing.Screen
import com.mxy.justaconverter.util.Utility
import kotlinx.coroutines.*

class ConvertScreenViewModel(
    var chooseFileType: ScaffoldContentViewModel.ChooseFileType,
    var from: String,
    var to: String,
    var filePath: Uri?,
    var convertButtonState: Boolean = true,
    var buttonText: String,
    context: Context
) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.Q)
    val convert: () -> Unit = {
        if (filePath == null || filePath?.equals(Uri.parse("")) == true) {
            Toast.makeText(
                context,
                context.getString(R.string.converter_screen_plz_choose_file),
                Toast.LENGTH_SHORT
            ).show()
            buttonText = context.getString(R.string.choose_file_screen_convert_button)
            convertButtonState = true
            JustAConverterRouter.navigateTo(Screen.TypeCardsScreen)
        } else {
            buttonText = context.getString(R.string.choose_file_screen_convert_button_converting)
            convertButtonState = false
            Log.d("mxy!!!", this.toString())
            viewModelScope.launch(Dispatchers.IO) {
                val downloadUrl =
                    Utility.getDownloadUrlFromFileOkHttp(context, filePath!!, from, to)
                if (downloadUrl == null) {
                    Log.d("mxy!!!", "downloadUrl 是空的！")
                    Toast.makeText(
                        context,
                        context.getString(R.string.converter_screen_convert_error),
                        Toast.LENGTH_SHORT
                    ).show()
                    buttonText = context.getString(R.string.choose_file_screen_convert_button)
                    convertButtonState = true
                } else {
                    Log.d("mxy!!!", downloadUrl)
                    buttonText = context.getString(R.string.choose_file_screen_convert_button)
                    convertButtonState = true
                    JustAConverterRouter.navigateTo(Screen.TypeCardsScreen)
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(downloadUrl)))
                }
            }
        }
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

    override fun toString(): String {
        return "ConvertScreenViewModel(from='$from', to='$to', filePath=$filePath, convertButtonState=$convertButtonState, buttonText='$buttonText')"
    }

}