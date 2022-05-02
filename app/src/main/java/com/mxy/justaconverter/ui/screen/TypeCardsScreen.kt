package com.mxy.justaconverter.ui.screen

import android.net.Uri
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.mxy.justaconverter.routing.JustAConverterRouter
import com.mxy.justaconverter.routing.Screen
import com.mxy.justaconverter.ui.element.BottomAppBar
import com.mxy.justaconverter.ui.element.TopAppBar
import com.mxy.justaconverter.ui.element.TypesCards
import com.mxy.justaconverter.viewmodel.BottomBarViewModel
import com.mxy.justaconverter.viewmodel.ConvertScreenViewModel
import com.mxy.justaconverter.viewmodel.ScaffoldContentViewModel
import kotlinx.coroutines.launch

@Composable
fun TypeCardsScreen(convertScreenViewModel: ConvertScreenViewModel) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(scaffoldState = scaffoldState, topBar = {
        TopAppBar {
            coroutineScope.launch {
                scaffoldState.drawerState.open()
            }
        }
    }, bottomBar = { BottomAppBar(state = BottomBarViewModel.BottomBarState.Converter) },
        drawerContent = {
            DrawerScreen(onDrawerCardClick = {
                val target = when (it) {
                    "Trash" -> Screen.TrashScreen
                    "About" -> Screen.AboutScreen
                    else -> Screen.TypeCardsScreen
                }
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
                JustAConverterRouter.navigateTo(target)
            })
        }, content = {
            TypesCards(onTypeCardClick = {
                val type = when(it) {
                    "Archive" -> ScaffoldContentViewModel.ChooseFileType.Archive
                    "Audio" -> ScaffoldContentViewModel.ChooseFileType.Audio
                    "Document" -> ScaffoldContentViewModel.ChooseFileType.Document
                    "E-Book" -> ScaffoldContentViewModel.ChooseFileType.Ebook
                    "Font" -> ScaffoldContentViewModel.ChooseFileType.Font
                    "Image" -> ScaffoldContentViewModel.ChooseFileType.Image
                    "Presentation" -> ScaffoldContentViewModel.ChooseFileType.Presentation
                    "Sheet" -> ScaffoldContentViewModel.ChooseFileType.Sheet
                    "Vector" -> ScaffoldContentViewModel.ChooseFileType.Vector
                    "Video" -> ScaffoldContentViewModel.ChooseFileType.Video
                    else -> ScaffoldContentViewModel.ChooseFileType.Archive
                }
                convertScreenViewModel.apply {
                    chooseFileType = type
                    from = "..."
                    to = "..."
                    filePath = Uri.parse("")
                }
                JustAConverterRouter.navigateTo(Screen.ConverterScreen)
            })
        })
}