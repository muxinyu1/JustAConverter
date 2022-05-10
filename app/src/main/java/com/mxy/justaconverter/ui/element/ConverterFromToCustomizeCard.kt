package com.mxy.justaconverter.ui.element

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.mxy.justaconverter.viewmodel.ScaffoldContentViewModel

@Composable
fun ConverterFromToCustomizeCard(
    modifier: Modifier,
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
        CustomizeFormatBox(
            label = "from",
            format = from,
            modifier = Modifier.widthIn(max = 100.dp).wrapContentHeight(),
            onFormatChange = onFromFormatChanged
        )
        Text(
            text = "TO",
            modifier = Modifier.align(alignment = Alignment.CenterVertically),
            fontStyle = FontStyle.Italic
        )
        CustomizeFormatBox(
            label = "to",
            format = to,
            modifier = Modifier.widthIn(max = 100.dp).wrapContentHeight(),
            onFormatChange = onToFormatChanged
        )
    }
}