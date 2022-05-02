package com.mxy.justaconverter.ui.screen

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mxy.justaconverter.routing.Screen
import com.mxy.justaconverter.ui.element.BottomAppBar
import com.mxy.justaconverter.ui.element.ConverterScreenScaffoldContent
import com.mxy.justaconverter.ui.element.TopAppBar
import com.mxy.justaconverter.viewmodel.BottomBarViewModel
import com.mxy.justaconverter.viewmodel.ScaffoldContentViewModel
import kotlinx.coroutines.launch

@Composable
fun ConverterScreen(
    chooseFileType: ScaffoldContentViewModel.ChooseFileType,
    fromStateFrom: MutableState<String>,
    fromStateTo: MutableState<String>,
    filePathState: MutableState<Uri?>,
    onConverterButtonClick: () -> Unit,
    enableState: MutableState<Boolean>
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
            chooseFileType = chooseFileType,
            modifier = Modifier.fillMaxWidth(),
            fromStateFrom = fromStateFrom,
            fromStateTo = fromStateTo,
            filePathState = filePathState,
            onConvertButtonClick = onConverterButtonClick,
            enableState = enableState
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

@Composable
@Preview(showBackground = true)
fun ConverterScreenPreview() {
    val uri = Uri.parse("")
    val fromStateFrom = remember {
        mutableStateOf("...")
    }
    val fromStateTo = remember {
        mutableStateOf("...")
    }
    val filePathState = remember {
        mutableStateOf(uri)
    }
    val enableState = remember {
        mutableStateOf(true)
    }
    ConverterScreen(
        chooseFileType = ScaffoldContentViewModel.ChooseFileType.Font,
        fromStateFrom = fromStateFrom,
        fromStateTo = fromStateTo,
        filePathState = filePathState,
        onConverterButtonClick = {  },
        enableState = enableState
    )
}