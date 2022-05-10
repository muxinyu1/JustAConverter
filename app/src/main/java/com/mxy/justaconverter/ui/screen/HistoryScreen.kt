package com.mxy.justaconverter.ui.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mxy.justaconverter.R
import com.mxy.justaconverter.routing.JustAConverterRouter
import com.mxy.justaconverter.routing.Screen
import com.mxy.justaconverter.ui.element.BottomAppBar
import com.mxy.justaconverter.ui.element.ToDoHint
import com.mxy.justaconverter.ui.element.TopAppBar
import com.mxy.justaconverter.viewmodel.BottomBarViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HistoryScreen(coroutineScope: CoroutineScope, context: Context) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState, topBar = {
        TopAppBar {
            coroutineScope.launch {
                scaffoldState.drawerState.open()
            }
        }
    }, bottomBar = { BottomAppBar(state = BottomBarViewModel.BottomBarState.History) },
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
        }, content = { ToDoHint() })
}