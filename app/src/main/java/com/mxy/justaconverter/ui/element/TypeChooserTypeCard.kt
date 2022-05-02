package com.mxy.justaconverter.ui.element

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mxy.justaconverter.R
import com.mxy.justaconverter.ui.theme.TypeCardBorderColor
import com.mxy.justaconverter.ui.theme.TypeCardImageColor
import com.mxy.justaconverter.ui.theme.TypeCardTextColorLight
import com.mxy.justaconverter.viewmodel.TypeCardViewModel

@Composable
fun TypeChooserTypeCard(
    modifier: Modifier,
    onTypeCardClick:  (String) -> Unit,
    typeCardViewModel: TypeCardViewModel
) {
    val cardShape = RoundedCornerShape(8.dp)
    Surface(
        modifier = modifier
            .padding(8.dp)
            .background(Color.Gray, cardShape)
            .border(border = BorderStroke(1.dp, TypeCardBorderColor), cardShape)
            .shadow(3.dp, cardShape)
            .fillMaxWidth()
            .heightIn(min = 70.dp)
            .clickable { onTypeCardClick(typeCardViewModel.typeName) }
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Column(modifier = Modifier.padding(start = 5.dp, top = 0.25.dp, bottom = 0.25.dp)) {
                Text(
                    text = typeCardViewModel.typeName,
                    color = TypeCardTextColorLight,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Column(Modifier.padding(start = 5.dp, top = 1.25.dp, bottom = 0.25.dp)) {
                Text(
                    text = typeCardViewModel.description,
                    color = TypeCardTextColorLight.copy(alpha = 0.7f),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 2.5.dp)
                )
            }
            Row(modifier = Modifier.align(alignment = Alignment.End)) {
                val painterId: Int = when (typeCardViewModel.typeName) {
                    stringResource(id = R.string.type_view_model_img) -> R.drawable.ic_type_image
                    stringResource(id = R.string.type_view_model_archive) -> R.drawable.ic_type_archive
                    stringResource(id = R.string.type_view_model_audio) -> R.drawable.ic_type_audio
                    stringResource(id = R.string.type_view_model_document) -> R.drawable.ic_type_document
                    stringResource(id = R.string.type_view_model_ebook) -> R.drawable.ic_type_ebook
                    stringResource(id = R.string.type_view_model_font) -> R.drawable.ic_type_font
                    stringResource(id = R.string.type_view_model_presentation) -> R.drawable.ic_type_presentation
                    stringResource(id = R.string.type_view_model_sheet) -> R.drawable.ic_type_sheet
                    stringResource(id = R.string.type_view_model_vector) -> R.drawable.ic_type_vector
                    stringResource(id = R.string.type_view_model_video) -> R.drawable.ic_type_video
                    else -> R.drawable.ic_type_image
                }
                Icon(
                    painter = painterResource(id = painterId),
                    contentDescription = "Type Convert Description",
                    tint = TypeCardImageColor,
                    modifier = Modifier.padding(end = 5.dp, bottom = 5.dp)
                )
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun TypeChooserTypeCardPreview() {
    TypeChooserTypeCard(
        modifier = Modifier,
        onTypeCardClick = {},
        typeCardViewModel = TypeCardViewModel.getDefault()
    )
}
