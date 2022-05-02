package com.mxy.justaconverter.ui.screen

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.mxy.justaconverter.ui.element.BottomAppBar
import com.mxy.justaconverter.ui.element.ScaffoldContent
import com.mxy.justaconverter.ui.element.TopAppBar
import com.mxy.justaconverter.viewmodel.BottomBarViewModel
import com.mxy.justaconverter.viewmodel.MainViewModel
import com.mxy.justaconverter.viewmodel.ScaffoldContentViewModel
import kotlinx.coroutines.launch

@Composable
fun TypeCardsScreen(
    mainViewModel: MainViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(onDrawerClick = {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            })

        },
        drawerContent = {
            DrawerScreen(onDrawerCardClick = {
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
            })
        },
        content = { ScaffoldContent(mainViewModel = mainViewModel) },
        bottomBar = {
            BottomAppBar(bottomBarViewModel = mainViewModel.bottomBarViewModel)
        },
        scaffoldState = scaffoldState
    )
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    val mainViewModel =
        MainViewModel(ScaffoldContentViewModel.getDefault(), BottomBarViewModel.getDefault())
    TypeCardsScreen(mainViewModel)
}
