package com.mxy.justaconverter.ui.element

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mxy.justaconverter.R
import com.mxy.justaconverter.ui.theme.ConverterConvertButtonColor

@Composable
fun ConverterConvertButton(
    modifier: Modifier,
    onConvertButtonClick: () -> Unit,
    enable: Boolean,
    text: String
) {
    val enableState = remember {
        mutableStateOf(enable)
    }
    val textState = remember {
        mutableStateOf(text)
    }
    Button(
        shape = CircleShape,
        modifier = modifier
            .padding(5.dp)
            .wrapContentHeight()
            .wrapContentWidth()
            .clip(CircleShape)
            .shadow(elevation = 1.dp, shape = CircleShape),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ConverterConvertButtonColor,
        ),
        onClick = {
            enableState.value = false
            textState.value = "Converting!"
            onConvertButtonClick()
        },
        enabled = enableState.value,
        border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.3f))
    ) {
        Row(modifier = Modifier.padding(5.dp)) {
            Text(
                modifier = Modifier.align(alignment = Alignment.CenterVertically),
                text = textState.value,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(5.dp))
            if (enableState.value) {
                Icon(
                    modifier = Modifier.align(alignment = Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.ic_choose_file_screen_right_arrow),
                    contentDescription = "Convert Arrow",
                    tint = Color.White
                )
            } else {
                LoadingAnimation(
                    modifier = Modifier.align(alignment = Alignment.CenterVertically),
                    circleSize = 5.dp,
                    spaceBetween = 2.dp,
                    circleColor = Color.White,
                    travelDistance = 4.dp
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ConverterConvertButtonPreview() {
    val enableState = remember {
        mutableStateOf(true)
    }
    ConverterConvertButton(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        onConvertButtonClick = { enableState.value = !enableState.value },
        enable = true,
        text = "Convert!"
    )
}