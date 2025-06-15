package com.techcognics.erpapp.data

import androidx.compose.ui.graphics.Color

data class StatCard(
    val title: String,
    val value: String,
    val unit: String,
    val borderColor: Color,
    val backgroundColor: Color,
    val chartRes: Int // Drawable resource ID for chart
)