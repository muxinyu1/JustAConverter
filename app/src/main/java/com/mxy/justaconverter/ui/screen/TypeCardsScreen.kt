package com.mxy.justaconverter.ui.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.mxy.justaconverter.R
import com.mxy.justaconverter.routing.JustAConverterRouter
import com.mxy.justaconverter.routing.Screen
import com.mxy.justaconverter.ui.element.BottomAppBar
import com.mxy.justaconverter.ui.element.TopAppBar
import com.mxy.justaconverter.ui.element.TypesCards
import com.mxy.justaconverter.viewmodel.BottomBarViewModel
import com.mxy.justaconverter.viewmodel.ConvertScreenViewModel
import com.mxy.justaconverter.viewmodel.ScaffoldContentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TypeCardsScreen(
    convertScreenViewModel: ConvertScreenViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {
    val scaffoldState = rememberScaffoldState()
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
                    context.getString(R.string.drawer_screen_card_trash) -> Screen.TrashScreen
                    context.getString(R.string.drawer_screen_card_about) -> Screen.AboutScreen
                    else -> Screen.JumpToWebsite
                }
                if (target != Screen.JumpToWebsite) {
                    JustAConverterRouter.navigateTo(target)
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                } else {
                    val website = when (it) {
                        context.getString(R.string.drawer_screen_card_github) -> context.getString(R.string.drawer_screen_project_github_site)
                        context.getString(R.string.drawer_screen_card_cloud_convert) -> context.getString(
                            R.string.drawer_screen_cloud_convert_site
                        )
                        context.getString(R.string.drawer_screen_card_anon_files) -> context.getString(
                            R.string.drawer_screen_anon_files_site
                        )
                        else -> null
                    }
                    website?.let {
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(website)))
                    }
                }
            })
        }, content = {
            TypesCards(onTypeCardClick = {
                val type = when (it) {
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
                Log.d(
                    "mxy!!!",
                    "点击了${it}卡片，现在convertScreenViewModel.chooseFileType = ${convertScreenViewModel.chooseFileType.name}"
                )
                JustAConverterRouter.navigateTo(Screen.ConverterScreen)
            })
        })
}