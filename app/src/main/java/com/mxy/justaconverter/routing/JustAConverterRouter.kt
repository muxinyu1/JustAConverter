package com.mxy.justaconverter.routing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mxy.justaconverter.viewmodel.ScaffoldContentViewModel

sealed class Screen {
    object AboutScreen: Screen()
    object ConverterScreen: Screen()
    object DrawerScreen: Screen()
    object HistoryScreen: Screen()
    object SettingsScreen: Screen()
    object TrashScreen: Screen()
    object TypeCardsScreen: Screen()
    object JumpToWebsite: Screen()
}

object JustAConverterRouter {
    var currentScreen: Screen by mutableStateOf(Screen.TypeCardsScreen)
    fun navigateTo(destination: Screen) {
        currentScreen = destination
    }
}
