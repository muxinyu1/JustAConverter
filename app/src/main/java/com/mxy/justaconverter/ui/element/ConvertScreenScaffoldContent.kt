package com.mxy.justaconverter.ui.element

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mxy.justaconverter.viewmodel.ScaffoldContentViewModel

@Composable
fun ConverterScreenScaffoldContent(
    chooseFileType: ScaffoldContentViewModel.ChooseFileType,
    modifier: Modifier,
    from: String,
    to: String,
    filePath: Uri?,
    onConvertButtonClick: () -> Unit,
    enable: Boolean,
    onFilePathChanged: (Uri?) -> Unit,
    onFromFormatChanged: (String) -> Unit,
    onToFormatChanged: (String) -> Unit,
    buttonText: String,
    isCustomized: Boolean
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ConverterTitle(
            chooseFileType = chooseFileType,
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally)
        )
        if (!isCustomized) {
            ConverterFromToCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally),
                chooseFileType = chooseFileType,
                from = from,
                to = to,
                onToFormatChanged = onToFormatChanged,
                onFromFormatChanged = onFromFormatChanged
            )
        } else {
            ConverterFromToCustomizeCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally),
                from = from,
                to = to,
                onFromFormatChanged = onFromFormatChanged,
                onToFormatChanged = onToFormatChanged
            )
        }
        ConverterFileChooser(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally),
            filePath = filePath,
            onFilePathChanged = onFilePathChanged
        )
        Spacer(modifier = Modifier.height(30.dp))
        ConverterConvertButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally),
            onConvertButtonClick = onConvertButtonClick,
            enable = enable,
            text = buttonText
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ConverterScreenScaffoldContentPreview() {
    val uri = Uri.parse("")
    val fromStateFrom = remember {
        mutableStateOf("")
    }
    val fromStateTo = remember {
        mutableStateOf("")
    }
    val filePathState = remember {
        mutableStateOf(uri)
    }
    val enableState = remember {
        mutableStateOf(true)
    }
    ConverterScreenScaffoldContent(
        chooseFileType = ScaffoldContentViewModel.ChooseFileType.Sheet,
        modifier = Modifier.fillMaxWidth(),
        from = fromStateFrom.value,
        to = fromStateTo.value,
        filePath = filePathState.value,
        onConvertButtonClick = { },
        enable = enableState.value,
        onFilePathChanged = {},
        onFromFormatChanged = {},
        onToFormatChanged = {},
        buttonText = "Convert!",
        isCustomized = true
    )
}