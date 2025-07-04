package com.techcognics.erpapp.data

import androidx.compose.ui.graphics.Color

data class SwitchableSingleLineAndBarCard(
    val listLineData:List<Double>,
    val listLineLabel:List<String>,
    val barListDate:List<BarDataCard>,
    val color: Color
)
