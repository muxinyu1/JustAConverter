package com.mxy.justaconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mxy.justaconverter.ui.element.ConverterChooseFormatPreview
import com.mxy.justaconverter.ui.element.ConverterConvertButtonPreview
import com.mxy.justaconverter.ui.element.ConverterFileChooserPreview
import com.mxy.justaconverter.ui.element.ConverterFromToCardPreview
import com.mxy.justaconverter.ui.screen.ConverterScreenPreview
import com.mxy.justaconverter.ui.screen.MainScreenPreview
import com.mxy.justaconverter.ui.theme.ConverterConvertButtonColor
import com.mxy.justaconverter.ui.theme.JustAConverterTheme

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
                    ConverterScreenPreview()
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