package com.mxy.justaconverter.ui.element

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mxy.justaconverter.R
import com.mxy.justaconverter.viewmodel.ScaffoldContentViewModel

@Composable
fun ConverterTitle(
    chooseFileType: ScaffoldContentViewModel.ChooseFileType,
    modifier: Modifier
) {
    val painter: Painter = getPainterFromChooseFileType(chooseFileType = chooseFileType)
    val description: String = getChooseFileDescription(chooseFileType = chooseFileType)
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = getChooseFileTypeName(chooseFileType = chooseFileType),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(alignment = CenterHorizontally)
                .padding(10.dp)
        )
        Icon(
            painter = painter,
            contentDescription = description,
            modifier = Modifier
                .padding(top = 40.dp, start = 30.dp, end = 30.dp, bottom = 30.dp)
                .align(alignment = CenterHorizontally)
                .scale(3.0f)
        )
        Spacer(modifier = modifier.height(15.dp))
        Text(
            text = description,
            color = Color.Black.copy(alpha = 0.7f),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            maxLines = 3,
            modifier = Modifier
                .padding(15.dp)
                .align(alignment = CenterHorizontally)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ConverterTitlePreview() {
    ConverterTitle(
        chooseFileType = ScaffoldContentViewModel.ChooseFileType.Presentation,
        modifier = Modifier
    )
}

@Composable
fun getPainterFromChooseFileType(chooseFileType: ScaffoldContentViewModel.ChooseFileType): Painter {
    return when (chooseFileType) {
        ScaffoldContentViewModel.ChooseFileType.Archive ->
            painterResource(id = R.drawable.ic_type_archive)
        ScaffoldContentViewModel.ChooseFileType.Audio ->
            painterResource(id = R.drawable.ic_type_audio)
        ScaffoldContentViewModel.ChooseFileType.Document ->
            painterResource(id = R.drawable.ic_type_document)
        ScaffoldContentViewModel.ChooseFileType.Ebook ->
            painterResource(id = R.drawable.ic_type_ebook)
        ScaffoldContentViewModel.ChooseFileType.Font ->
            painterResource(id = R.drawable.ic_type_font)
        ScaffoldContentViewModel.ChooseFileType.Image ->
            painterResource(id = R.drawable.ic_type_image)
        ScaffoldContentViewModel.ChooseFileType.Presentation ->
            painterResource(id = R.drawable.ic_type_presentation)
        ScaffoldContentViewModel.ChooseFileType.Sheet ->
            painterResource(id = R.drawable.ic_type_sheet)
        ScaffoldContentViewModel.ChooseFileType.Vector ->
            painterResource(id = R.drawable.ic_type_vector)
        ScaffoldContentViewModel.ChooseFileType.Video ->
            painterResource(id = R.drawable.ic_type_video)
        ScaffoldContentViewModel.ChooseFileType.Customize ->
            painterResource(id = R.drawable.ic_type_customized)
    }
}

@Composable
fun getChooseFileDescription(chooseFileType: ScaffoldContentViewModel.ChooseFileType): String {
    return when (chooseFileType) {
        ScaffoldContentViewModel.ChooseFileType.Archive ->
            stringResource(id = R.string.choose_file_screen_archive_description)
        ScaffoldContentViewModel.ChooseFileType.Audio ->
            stringResource(id = R.string.choose_file_screen_audio_description)
        ScaffoldContentViewModel.ChooseFileType.Document ->
            stringResource(id = R.string.choose_file_screen_document_description)
        ScaffoldContentViewModel.ChooseFileType.Ebook ->
            stringResource(id = R.string.choose_file_screen_ebook_description)
        ScaffoldContentViewModel.ChooseFileType.Font ->
            stringResource(id = R.string.choose_file_screen_font_description)
        ScaffoldContentViewModel.ChooseFileType.Image ->
            stringResource(id = R.string.choose_file_screen_image_description)
        ScaffoldContentViewModel.ChooseFileType.Presentation ->
            stringResource(id = R.string.choose_file_screen_presentation_description)
        ScaffoldContentViewModel.ChooseFileType.Sheet ->
            stringResource(id = R.string.choose_file_screen_sheet_description)
        ScaffoldContentViewModel.ChooseFileType.Vector ->
            stringResource(id = R.string.choose_file_screen_vector_description)
        ScaffoldContentViewModel.ChooseFileType.Video ->
            stringResource(id = R.string.choose_file_screen_video_description)
        ScaffoldContentViewModel.ChooseFileType.Customize ->
            stringResource(id = R.string.choose_file_screen_customized_description)
    }
}

@Composable
fun getChooseFileTypeName(chooseFileType: ScaffoldContentViewModel.ChooseFileType): String {
    return when (chooseFileType) {
        ScaffoldContentViewModel.ChooseFileType.Archive ->
            stringResource(id = R.string.type_view_model_archive)
        ScaffoldContentViewModel.ChooseFileType.Audio ->
            stringResource(id = R.string.type_view_model_audio)
        ScaffoldContentViewModel.ChooseFileType.Document ->
            stringResource(id = R.string.type_view_model_document)
        ScaffoldContentViewModel.ChooseFileType.Ebook ->
            stringResource(id = R.string.type_view_model_ebook)
        ScaffoldContentViewModel.ChooseFileType.Font ->
            stringResource(id = R.string.type_view_model_font)
        ScaffoldContentViewModel.ChooseFileType.Image ->
            stringResource(id = R.string.type_view_model_img)
        ScaffoldContentViewModel.ChooseFileType.Presentation ->
            stringResource(id = R.string.type_view_model_presentation)
        ScaffoldContentViewModel.ChooseFileType.Sheet ->
            stringResource(id = R.string.type_view_model_sheet)
        ScaffoldContentViewModel.ChooseFileType.Vector ->
            stringResource(id = R.string.type_view_model_vector)
        ScaffoldContentViewModel.ChooseFileType.Video ->
            stringResource(id = R.string.type_view_model_video)
        ScaffoldContentViewModel.ChooseFileType.Customize ->
            stringResource(id = R.string.type_view_model_customize)
    }
}