package com.mxy.justaconverter.ui.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mxy.justaconverter.R
import com.mxy.justaconverter.ui.theme.LightColorDefault

@Composable
fun TopAppBar(onDrawerClick: () -> Unit) {

    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        LightColorDefault,
                        Color.White
                    )
                )
            )
    ) {
        IconButton(onClick = onDrawerClick, content = {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Drawer Menu",
                modifier = Modifier.padding(5.dp),
                tint = MaterialTheme.colors.onPrimary
            )
        }, modifier = Modifier.align(alignment = Alignment.CenterVertically))
        Text(
            text = stringResource(id = R.string.app_name),
            color = MaterialTheme.colors.onPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(start = 5.dp)
        )
    }
}

@Composable
@Preview
fun TopAppBarPreview() {
    TopAppBar(onDrawerClick = {})
}