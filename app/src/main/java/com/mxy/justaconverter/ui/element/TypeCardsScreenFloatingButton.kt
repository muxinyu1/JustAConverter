package com.mxy.justaconverter.ui.element

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mxy.justaconverter.ui.theme.LightColorDefault

@Composable
fun TypeCardsScreenFloatingButton(onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = Modifier.size(50.dp)) {
        Icon(
            imageVector = Icons.Default.Add,
            tint = Color.White,
            contentDescription = "Customize Converter",
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp)
                .background(color = LightColorDefault.copy(alpha = 0.7f))
                .border(BorderStroke(0.5.dp, color = Color.Black), shape = CircleShape)
        )
    }
}