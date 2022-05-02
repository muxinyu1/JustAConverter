package com.mxy.justaconverter.ui.element

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mxy.justaconverter.R
import com.mxy.justaconverter.ui.theme.LightColorDefault

@Composable
fun DrawerScreenTitle(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .padding(5.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_drawer_screen_title),
            contentDescription = "Drawer Screen Title",
            modifier = Modifier
                .padding(5.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.app_name),
            color = LightColorDefault,
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp
        )
    }
}

@Composable
@Preview
fun DrawerScreenTitlePreview() {
    DrawerScreenTitle()
}