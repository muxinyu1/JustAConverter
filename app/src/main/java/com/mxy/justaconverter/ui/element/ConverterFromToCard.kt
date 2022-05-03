package com.mxy.justaconverter.ui.element

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mxy.justaconverter.viewmodel.ScaffoldContentViewModel

@Composable
fun ConverterFromToCard(
    modifier: Modifier,
    chooseFileType: ScaffoldContentViewModel.ChooseFileType,
    from: String,
    to: String,
    onFromFormatChanged: (String) -> Unit,
    onToFormatChanged: (String) -> Unit
) {
    Row(
        modifier = modifier
            .padding(5.dp)
            .wrapContentWidth()
    ) {
        Text(
            text = "From",
            modifier = Modifier.align(alignment = Alignment.CenterVertically),
            fontStyle = FontStyle.Italic
        )
        ConverterChooseFormat(
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(4.dp),
            chooseFileType = chooseFileType,
            format = from,
            onFormatChanged = onFromFormatChanged
        )
        Text(
            text = "TO",
            modifier = Modifier.align(alignment = Alignment.CenterVertically),
            fontStyle = FontStyle.Italic
        )
        ConverterChooseFormat(
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(4.dp),
            chooseFileType = chooseFileType,
            format = to,
            onFormatChanged = onToFormatChanged
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ConverterFromToCardPreview() {
    val formatStateFrom = remember {
        mutableStateOf("...")
    }
    val formatStateTo = remember {
        mutableStateOf("...")
    }
    ConverterFromToCard(
        modifier = Modifier.fillMaxWidth(),
        chooseFileType = ScaffoldContentViewModel.ChooseFileType.Audio,
        from = formatStateFrom.value,
        to = formatStateTo.value,
        onFromFormatChanged = {},
        onToFormatChanged = {}
    )
}