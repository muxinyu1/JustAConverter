package com.mxy.justaconverter.ui.element

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mxy.justaconverter.R
import com.mxy.justaconverter.routing.JustAConverterRouter
import com.mxy.justaconverter.routing.Screen
import com.mxy.justaconverter.viewmodel.BottomBarViewModel

@Composable
fun BottomAppBar(state: BottomBarViewModel.BottomBarState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
    ) {
        Divider(
            color = Color.Black.copy(alpha = 0.2f),
            modifier = Modifier.fillMaxWidth(),
        )
        Row(
            modifier = Modifier
                .padding(5.dp)
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            BottomAppBarItem(
                itemName = stringResource(id = R.string.bottom_bar_converter_item),
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .weight(1 / 3f)
                    .wrapContentHeight(),
                state = state
            )
            BottomAppBarItem(
                itemName = stringResource(id = R.string.bottom_bar_history_item),
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .weight(1 / 3f)
                    .wrapContentHeight(),
                state = state
            )
            BottomAppBarItem(
                itemName = stringResource(id = R.string.bottom_bar_settings_item),
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .weight(1 / 3f)
                    .wrapContentHeight(),
                state = state
            )
        }
    }
}


@Composable
fun BottomAppBarItem(
    state: BottomBarViewModel.BottomBarState,
    itemName: String,
    modifier: Modifier
) {
    val painter = when (itemName) {
        stringResource(id = R.string.bottom_bar_converter_item) -> painterResource(id = R.drawable.ic_bottom_bar_converter)
        stringResource(id = R.string.bottom_bar_history_item) -> painterResource(id = R.drawable.ic_bottom_bar_history)
        stringResource(id = R.string.bottom_bar_settings_item) -> painterResource(id = R.drawable.ic_bottom_bar_settings)
        else -> painterResource(id = R.drawable.ic_bottom_bar_converter)
    }
    val rightState = when(itemName) {
        stringResource(id = R.string.bottom_bar_converter_item) -> BottomBarViewModel.BottomBarState.Converter
        stringResource(id = R.string.bottom_bar_history_item) -> BottomBarViewModel.BottomBarState.History
        stringResource(id = R.string.bottom_bar_settings_item) -> BottomBarViewModel.BottomBarState.Settings
        else -> BottomBarViewModel.BottomBarState.Settings
    }
    Column(modifier = modifier
        .padding(5.dp)
        .clip(CircleShape)
        .clickable {
            val target = when(rightState) {
                BottomBarViewModel.BottomBarState.Converter -> Screen.TypeCardsScreen
                BottomBarViewModel.BottomBarState.Settings -> Screen.SettingsScreen
                BottomBarViewModel.BottomBarState.History -> Screen.HistoryScreen
            }
            JustAConverterRouter.navigateTo(target)
        }) {
        Icon(
            painter = painter,
            contentDescription = "Bottom Bar Converter Item",
            modifier = Modifier
                .wrapContentHeight()
                .align(alignment = Alignment.CenterHorizontally),
            tint = if (state == rightState) Color.Black else Color.Black.copy(
                alpha = 0.3f
            )
        )
        Text(
            text = itemName,
            modifier = Modifier
                .wrapContentHeight()
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 7.dp),
            fontSize = 14.sp,
            fontWeight = if (state == rightState) FontWeight.Bold else FontWeight.Normal,
            color = Color.Black.copy(alpha = 0.8f)
        )
    }
}

@Composable
@Preview
fun BottomAppBarPreview() {
    BottomAppBar(state = BottomBarViewModel.BottomBarState.Settings)
}
