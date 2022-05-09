package com.mxy.justaconverter.ui.element

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.mxy.justaconverter.ui.theme.LightColorDefault

@Composable
fun CustomizeFormatBox(
    modifier: Modifier = Modifier,
    label: String,
    format: String,
    onFormatChange: (String) -> Unit
) {
    val formatState = remember {
        mutableStateOf(format)
    }
    OutlinedTextField(
        modifier = modifier,
        value = formatState.value,
        onValueChange = { formatState.value = it; onFormatChange(it) },
        label = { Text(text = label) },
        placeholder = { Text(text = "Input $label Format") },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = LightColorDefault,
            focusedLabelColor = LightColorDefault,
            cursorColor = LightColorDefault
        )
    )
}

@Composable
@Preview(showBackground = true)
fun CustomizeFormatBoxPreview() {
    CustomizeFormatBox(modifier = Modifier, label = "from", format = ""){}
}