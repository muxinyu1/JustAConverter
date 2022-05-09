package com.mxy.justaconverter.ui.element

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mxy.justaconverter.R

@Composable
@Preview
fun DrawerCardPreview() {
    DrawerCard(
        cardName = stringResource(R.string.drawer_screen_card_github),
        isLinkOut = true,
        onCardClick = {}
    )
}

@Composable
fun DrawerCard(
    cardName: String,
    isLinkOut: Boolean,
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val cardShape = RoundedCornerShape(4.dp)
    val painter = when (cardName) {
        stringResource(id = R.string.drawer_screen_card_trash) -> painterResource(id = R.drawable.ic_drawer_screen_trash)
        stringResource(id = R.string.drawer_screen_card_about) -> painterResource(id = R.drawable.ic_drawer_screen_about)
        stringResource(id = R.string.drawer_screen_card_github) -> painterResource(id = R.drawable.ic_drawer_screen_github)
        stringResource(id = R.string.drawer_screen_card_cloud_convert) -> painterResource(
            id = R.drawable.ic_drawer_screen_cloudconvert
        )
        stringResource(id = R.string.drawer_screen_card_link_out) -> painterResource(id = R.drawable.ic_drawer_screen_linkout)
        stringResource(id = R.string.drawer_screen_card_anon_files) -> painterResource(id = R.drawable.ic_drawer_screen_anon_files)
        else -> painterResource(id = R.drawable.ic_drawer_screen_title)
    }
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .border(BorderStroke(1.dp, Color.Gray.copy(alpha = 0.7f)), cardShape)
            .clickable { onCardClick(cardName) }
    ) {
        Row(modifier = Modifier.padding(5.dp)) {
            Icon(
                painter = painter,
                contentDescription = "Drawer Card Item: $cardName",
                modifier = Modifier
                    .padding(5.dp)
                    .align(alignment = Alignment.CenterVertically)
            )
            Text(
                text = cardName,
                modifier = Modifier
                    .padding(5.dp)
                    .align(alignment = Alignment.CenterVertically)
            )
            if (isLinkOut) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.CenterVertically)
                ) {
                    Spacer(modifier = Modifier.weight(0.8f))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_drawer_screen_linkout),
                        contentDescription = "Drawer Card Item: LinkOut",
                        modifier = Modifier
                            .weight(0.2f)
                            .padding(end = 5.dp)
                            .align(alignment = Alignment.CenterVertically),
                        tint = Color.Black.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }

}