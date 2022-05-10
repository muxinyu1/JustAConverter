package com.mxy.justaconverter.ui.element

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mxy.justaconverter.R

@Composable
fun ToDoHint(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(50.dp))
        Image(
            modifier = modifier
                .align(alignment = Alignment.CenterHorizontally)
                .size(50.dp),
            painter = painterResource(id = R.drawable.ic_todo_building),
            contentDescription = "This part is still in building..."
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = stringResource(id = R.string.todo_hint_building),
            fontSize = 16.sp,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            color = Color.Black.copy(alpha = 0.7f)
        )
    }
}