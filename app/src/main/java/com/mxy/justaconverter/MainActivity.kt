package com.mxy.justaconverter

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mxy.justaconverter.routing.JustAConverterRouter
import com.mxy.justaconverter.routing.Screen
import com.mxy.justaconverter.ui.screen.ConverterScreen
import com.mxy.justaconverter.ui.screen.TypeCardsScreen
import com.mxy.justaconverter.ui.theme.JustAConverterTheme
import com.mxy.justaconverter.viewmodel.ConvertScreenViewModel
import com.mxy.justaconverter.viewmodel.ScaffoldContentViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JustAConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val coroutineScope = rememberCoroutineScope()
                    val convertScreenViewModel = ConvertScreenViewModel(
                        ScaffoldContentViewModel.ChooseFileType.Archive,
                        "...",
                        "...",
                        Uri.parse(""),
                        context = this
                    )
                    val convertScreenViewModelState = remember {
                        mutableStateOf(convertScreenViewModel)
                    }
                    when (JustAConverterRouter.currentScreen) {
                        is Screen.TypeCardsScreen -> TypeCardsScreen(
                            convertScreenViewModel = convertScreenViewModelState.value,
                            coroutineScope = coroutineScope
                        )
                        is Screen.ConverterScreen -> ConverterScreen(
                            convertScreenViewModelState.value,
                            coroutineScope = coroutineScope
                        )
                        else -> Text(text = "Else")
                    }
                }
            }
        }
    }


}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JustAConverterTheme {
        Greeting("Android")
    }
}