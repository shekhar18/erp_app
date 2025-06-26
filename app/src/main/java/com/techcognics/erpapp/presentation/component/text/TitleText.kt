package com.techcognics.erpapp.presentation.component.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.graphics.Color

@Composable
fun TitleText(text: String, color: Color = Color.Black) {
    Text(
        text = text, style = MaterialTheme.typography.headlineSmall, color = color
    )
}