package com.mxy.justaconverter.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mxy.justaconverter.R
import com.mxy.justaconverter.ui.element.DrawerCard
import com.mxy.justaconverter.ui.element.DrawerScreenTitle

@Composable
fun DrawerScreen(onDrawerCardClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
    ) {
        DrawerScreenTitle(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
        )
        DrawerCard(
            cardName = stringResource(R.string.drawer_screen_card_trash),
            isLinkOut = false,
            onCardClick = onDrawerCardClick
        )
        DrawerCard(
            cardName = stringResource(R.string.drawer_screen_card_about),
            isLinkOut = false,
            onCardClick = onDrawerCardClick
        )
        Divider(
            modifier = Modifier
                .padding(5.dp),
            color = Color.Black.copy(alpha = 0.3f)
        )
        DrawerCard(
            cardName = stringResource(R.string.drawer_screen_card_github),
            isLinkOut = true,
            onCardClick = onDrawerCardClick
        )
        DrawerCard(
            cardName = stringResource(R.string.drawer_screen_card_cloud_convert),
            isLinkOut = true,
            onCardClick = onDrawerCardClick
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DrawerScreenPreview() {
    DrawerScreen(onDrawerCardClick = {})
}