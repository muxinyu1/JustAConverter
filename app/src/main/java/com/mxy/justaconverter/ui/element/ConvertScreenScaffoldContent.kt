package com.mxy.justaconverter.ui.element

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
    fromStateFrom: MutableState<String>,
    fromStateTo: MutableState<String>,
    filePathState: MutableState<Uri?>,
    onConvertButtonClick: () -> Unit,
    enableState: MutableState<Boolean>
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ConverterTitle(
            chooseFileType = chooseFileType,
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally)
        )
        ConverterFromToCard(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally),
            chooseFileType = chooseFileType,
            formatStateFrom = fromStateFrom,
            formatStateTo = fromStateTo
        )
        ConverterFileChooser(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally),
            filePathState = filePathState
        )
        Spacer(modifier = Modifier.height(30.dp))
        ConverterConvertButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally),
            onConvertButtonClick = onConvertButtonClick,
            enableState = enableState
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ConverterScreenScaffoldContentPreview() {
    val uri = Uri.parse("")
    val fromStateFrom = remember {
        mutableStateOf("...")
    }
    val fromStateTo = remember {
        mutableStateOf("...")
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
        fromStateFrom = fromStateFrom,
        fromStateTo = fromStateTo,
        filePathState = filePathState,
        onConvertButtonClick = { },
        enableState = enableState
    )
}