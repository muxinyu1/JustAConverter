package com.mxy.justaconverter.ui.element

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mxy.justaconverter.R
import com.mxy.justaconverter.viewmodel.ScaffoldContentViewModel

@Composable
fun ConverterChooseFormat(
    modifier: Modifier,
    chooseFileType: ScaffoldContentViewModel.ChooseFileType,
    format: String,
    onFormatChanged: (String) -> Unit
) {
    val formatState = remember {
        mutableStateOf(format)
    }
    val expandState = remember {
        mutableStateOf(false)
    }
    val formatList = getFormatList(chooseFileType)
    val shape = RoundedCornerShape(5.dp)
    Surface(
        modifier = modifier
            .padding(5.dp)
            .clip(shape)
            .shadow(shape = shape, elevation = 0.5.dp)
            .clickable {
                //Log.d("chooseFormat: ", "${!expandState.value}")
                expandState.value = !expandState.value
            },
        shape = shape,
        border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.5f))
    ) {
        Row(modifier = Modifier.padding(5.dp)) {
            Text(
                text = formatState.value,
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .scale(0.5f),
                painter = painterResource(id = R.drawable.ic_choose_format_dropdown),
                contentDescription = ""
            )
        }
        DropdownMenu(expanded = expandState.value,
            onDismissRequest = { expandState.value = false }) {
            for (formatName in formatList) {
                DropdownMenuItem(onClick = {
                    formatState.value = formatName
                    onFormatChanged(formatName)
                    expandState.value = false
                }) {
                    Text(text = formatName)
                }
            }
        }
    }
}

fun getFormatList(chooseFileType: ScaffoldContentViewModel.ChooseFileType): List<String> {
    return when (chooseFileType) {
        ScaffoldContentViewModel.ChooseFileType.Archive ->
            listOf(
                "7Z",
                "ACE",
                "ALZ",
                "ARC",
                "ARJ",
                "BZ",
                "BZ2",
                "CAB",
                "CPIO",
                "DEB",
                "DMG",
                "GZ",
                "IMG",
                "ISO",
                "JAR",
                "LHA",
                "LZ",
                "LZMA",
                "LZO",
                "RAR",
                "RPM",
                "RZ",
                "TAR",
                "TAR.7Z",
                "TAR.BZ",
                "TAR.BZ2",
                "TAR.GZ",
                "TAR.LZO",
                "TAR.XZ",
                "TAR.Z",
                "TBZ",
                "TBZ2",
                "TGZ",
                "TZ",
                "TZO",
                "XZ",
                "Z",
                "ZIP"
            )
        ScaffoldContentViewModel.ChooseFileType.Audio ->
            listOf(
                "AAC",
                "AC3",
                "AIF",
                "AIFC",
                "AIFF",
                "AMR",
                "AU",
                "CAF",
                "FLAC",
                "M4A",
                "M4B",
                "MP3",
                "OGA",
                "SFARK",
                "VOC",
                "WAV",
                "WEBA",
                "WMA"
            )
        ScaffoldContentViewModel.ChooseFileType.Document ->
            listOf(
                "ABW",
                "DJVU",
                "DOC",
                "DOCM",
                "DOCX",
                "DOT",
                "DOTX",
                "HTML",
                "HWP",
                "LWP",
                "MD",
                "ODT",
                "PAGES",
                "PDF",
                "RST",
                "RTF",
                "SDW",
                "TEX",
                "TXT",
                "WPD",
                "WPS",
                "ZABW"
            )
        ScaffoldContentViewModel.ChooseFileType.Ebook ->
            listOf("AZW", "AZW3", "AZW4", "CHM", "EPUB", "MOBI")
        ScaffoldContentViewModel.ChooseFileType.Font ->
            listOf("EOT", "OTF", "TTF", "WOFF", "WOFF2")
        ScaffoldContentViewModel.ChooseFileType.Image ->
            listOf(
                "BMP",
                "CRW",
                "EPS",
                "GIF",
                "ICO",
                "JFIF",
                "JPEG",
                "JPG",
                "ODD",
                "PNG",
                "PS",
                "PSD",
                "RAW",
                "TIF",
                "TIFF",
                "WEBP",
                "XPS"
            )
        ScaffoldContentViewModel.ChooseFileType.Presentation ->
            listOf("DPS", "ODP", "PPS", "PPT", "PPTX")
        ScaffoldContentViewModel.ChooseFileType.Sheet ->
            listOf("CSV", "ET", "ODS", "XLS", "XLSM", "XLSX")
        ScaffoldContentViewModel.ChooseFileType.Vector ->
            listOf("AI", "CDR", "SVG", "VSD")
        ScaffoldContentViewModel.ChooseFileType.Video ->
            listOf(
                "3GP",
                "AVI",
                "DV",
                "FLV",
                "M4V",
                "MKV",
                "MOV",
                "MP4",
                "MPEG",
                "MPG",
                "OOG",
                "RMVB",
                "SWF",
                "WMV"
            )
        ScaffoldContentViewModel.ChooseFileType.Customize ->
            listOf()
    }
}

@Composable
@Preview(showBackground = true)
fun ConverterChooseFormatPreview() {
    val formatState = remember {
        mutableStateOf("...")
    }
    ConverterChooseFormat(
        modifier = Modifier.wrapContentHeight(),
        chooseFileType = ScaffoldContentViewModel.ChooseFileType.Archive,
        format = formatState.value,
        onFormatChanged = {}
    )
}