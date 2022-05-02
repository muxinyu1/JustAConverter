package com.mxy.justaconverter.ui.element

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mxy.justaconverter.R
import com.mxy.justaconverter.viewmodel.TypeCardViewModel

@Composable
fun TypesCards(onTypeCardClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        TypeChooserGroup(
            firstType = stringResource(R.string.type_view_model_archive),
            secondType = stringResource(R.string.type_view_model_audio),
            onTypeCardClick = onTypeCardClick
        )
        TypeChooserGroup(
            firstType = stringResource(R.string.type_view_model_document),
            secondType = stringResource(R.string.type_view_model_ebook),
            onTypeCardClick = onTypeCardClick
        )
        TypeChooserGroup(
            firstType = stringResource(R.string.type_view_model_font),
            secondType = stringResource(R.string.type_view_model_img),
            onTypeCardClick = onTypeCardClick
        )
        TypeChooserGroup(
            firstType = stringResource(R.string.type_view_model_presentation),
            secondType = stringResource(R.string.type_view_model_sheet),
            onTypeCardClick = onTypeCardClick
        )
        TypeChooserGroup(
            firstType = stringResource(R.string.type_view_model_vector),
            secondType = stringResource(R.string.type_view_model_video),
            onTypeCardClick = onTypeCardClick
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(90.dp))
    }
}

@Composable
private fun getDescription(typeName: String): String {
    return when (typeName) {
        stringResource(R.string.type_view_model_archive) ->
            stringResource(R.string.type_view_model_archive_description)
        stringResource(R.string.type_view_model_audio) ->
            stringResource(R.string.type_view_model_audio_description)
        stringResource(R.string.type_view_model_document) ->
            stringResource(R.string.type_view_model_document_description)
        stringResource(R.string.type_view_model_ebook) ->
            stringResource(R.string.type_view_model_ebook_description)
        stringResource(R.string.type_view_model_font) ->
            stringResource(R.string.type_view_model_font_description)
        stringResource(R.string.type_view_model_img) ->
            stringResource(R.string.type_view_model_img_description)
        stringResource(R.string.type_view_model_presentation) ->
            stringResource(R.string.type_view_model_presentation_description)
        stringResource(R.string.type_view_model_sheet) ->
            stringResource(R.string.type_view_model_sheet_description)
        stringResource(R.string.type_view_model_vector) ->
            stringResource(R.string.type_view_model_vector_description)
        stringResource(R.string.type_view_model_video) ->
            stringResource(R.string.type_view_model_video_description)
        else -> stringResource(R.string.type_view_model_img_description)
    }
}

@Composable
private fun TypeChooserGroup(
    firstType: String,
    secondType: String,
    onTypeCardClick: (String) -> Unit
) {

    val firstTypeDescription = getDescription(firstType)
    val secondTypeDescription = getDescription(secondType)
    val firstTypeCardViewModelState = remember {
        mutableStateOf(TypeCardViewModel(firstType, firstTypeDescription))
    }
    val secondTypeCardViewModelState = remember {
        mutableStateOf(TypeCardViewModel(secondType, secondTypeDescription))
    }
    Row(modifier = Modifier.padding(10.dp)) {
        TypeChooserTypeCard(
            modifier = Modifier.weight(1 / 2f),
            onTypeCardClick = onTypeCardClick,
            typeCardViewModel = firstTypeCardViewModelState.value
        )
        TypeChooserTypeCard(
            modifier = Modifier.weight(1 / 2f),
            onTypeCardClick = onTypeCardClick,
            typeCardViewModel = secondTypeCardViewModelState.value
        )
    }
}

@Composable
@Preview
fun ConverterContentPreview() {
    TypesCards(onTypeCardClick = {})
}

@Composable
@Preview
fun TypeChooserGroupPreview() {
    TypeChooserGroup(
        stringResource(R.string.type_view_model_vector),
        stringResource(R.string.type_view_model_document),
        onTypeCardClick = {}
    )
}