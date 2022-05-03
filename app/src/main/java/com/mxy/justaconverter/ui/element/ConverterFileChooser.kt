package com.mxy.justaconverter.ui.element

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mxy.justaconverter.R
import com.mxy.justaconverter.ui.theme.LightColorDefault

@Composable
fun ConverterFileChooser(modifier: Modifier, filePath: Uri?, onFilePathChanged: (Uri?) -> Unit) {
    var text by remember {
        mutableStateOf("")
    }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        onFilePathChanged(uri)
        Log.d("mxy!!!", "filePath = ${filePath?.path}")
        when {
            uri == null -> {
                text = ""
            }
            uri.path == null -> {
                text = ""
            }
            uri.path != null -> {
                text = uri.path.toString()
            }
        }
    }
    Row(
        modifier = modifier
            .padding(5.dp)
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_converter_file_chooser_link),
            contentDescription = "Converter Choose File Link",
            modifier = Modifier
                .padding(4.dp)
                .align(alignment = CenterVertically)
        )
        OutlinedTextField(
            maxLines = 2,
            readOnly = true,
            modifier = Modifier
                .align(alignment = CenterVertically)
                .widthIn(max = 200.dp),
            label = { Text(text = stringResource(id = R.string.choose_file_screen_outline_text_field_label)) },
            value = text,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = LightColorDefault,
                focusedLabelColor = LightColorDefault,
                cursorColor = LightColorDefault
            ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Uri),
            onValueChange = {  }
        )
        Button(
            onClick = {
                  launcher.launch("*/*")
            },
            modifier = Modifier
                .align(alignment = CenterVertically)
                .padding(10.dp),
            shape = CircleShape,
            border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.3f)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Gray.copy(alpha = 0.3f)
            )
        ) {
            Text(
                text = stringResource(id = R.string.choose_file_screen_choose_file_button),
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }

    }
}