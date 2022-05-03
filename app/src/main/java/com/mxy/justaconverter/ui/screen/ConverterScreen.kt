package com.mxy.justaconverter.ui.screen

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.mxy.justaconverter.ui.element.BottomAppBar
import com.mxy.justaconverter.ui.element.ConverterScreenScaffoldContent
import com.mxy.justaconverter.ui.element.TopAppBar
import com.mxy.justaconverter.viewmodel.BottomBarViewModel
import com.mxy.justaconverter.viewmodel.ConvertScreenViewModel
import com.mxy.justaconverter.viewmodel.ScaffoldContentViewModel
import kotlinx.coroutines.launch

@Composable
fun ConverterScreen(convertScreenViewModel: ConvertScreenViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(topBar = {
        TopAppBar(onDrawerClick = {
            coroutineScope.launch {
                scaffoldState.drawerState.open()
            }
        })
    }, content = {
        ConverterScreenScaffoldContent(
            chooseFileType = convertScreenViewModel.chooseFileType,
            modifier = Modifier.fillMaxWidth(),
            from = convertScreenViewModel.from,
            to = convertScreenViewModel.to,
            filePath = convertScreenViewModel.filePath,
            onConvertButtonClick = convertScreenViewModel.convert,
            enable = convertScreenViewModel.convertButtonState,
            onFilePathChanged = convertScreenViewModel.onFilePathChanged,
            onFromFormatChanged = convertScreenViewModel.onFromFormatChanged,
            onToFormatChanged = convertScreenViewModel.onToFormatChanged
        )
    },
        drawerContent = { DrawerScreen(onDrawerCardClick = {
            coroutineScope.launch {
                scaffoldState.drawerState.close()
            }
        })
        },
        bottomBar = { BottomAppBar(state = BottomBarViewModel.BottomBarState.Converter)}
    )
}

