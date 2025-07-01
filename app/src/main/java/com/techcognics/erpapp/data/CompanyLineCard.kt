package com.techcognics.erpapp.data


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

data class CompanyLineCard(
    val label: String,
    val dataList: List<Double> = emptyList<Double>(),
    val firstGradientFillColor: Color,
    val secondGradientFillColor: Color,
    val lineThickness: Dp
)
