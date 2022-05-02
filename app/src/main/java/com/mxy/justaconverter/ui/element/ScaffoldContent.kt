package com.mxy.justaconverter.ui.element

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.mxy.justaconverter.viewmodel.MainViewModel
import com.mxy.justaconverter.viewmodel.ScaffoldContentViewModel

@Composable
fun ScaffoldContent(mainViewModel: MainViewModel) {
    val scaffoldContentState by remember {
        mutableStateOf(mainViewModel.scaffoldContentViewModel.scaffoldContentState)
    }
    val chooseFileType by remember {
        mutableStateOf(mainViewModel.scaffoldContentViewModel.chooseFileType)
    }
    if (scaffoldContentState != ScaffoldContentViewModel.ScaffoldContentState.ChooseFile) {
        when (scaffoldContentState) {
            ScaffoldContentViewModel.ScaffoldContentState.TypeCards ->
                TypesCards(onTypeCardClick = mainViewModel.scaffoldContentViewModel.onTypeCardClick)
            ScaffoldContentViewModel.ScaffoldContentState.Settings ->
                Text(text = "Settings")
            ScaffoldContentViewModel.ScaffoldContentState.History ->
                Text(text = "history")
            else -> Text(text = "??!!")
        }
    }
    else {
        when (chooseFileType) {
            ScaffoldContentViewModel.ChooseFileType.Audio ->
                Text(text = "Audio")
            ScaffoldContentViewModel.ChooseFileType.Archive ->
                Text(text = "Archive")
            else -> Text(text = "Else")
        }
    }
}

@Composable
@Preview
fun ScaffoldContentPreview() {

}