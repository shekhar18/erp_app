package com.techcognics.erpapp.data

import androidx.compose.ui.graphics.Color


data class SwitchableMultipleLineAndBar(
    val listOfIncome:List<Double>,
    val listOfExpenses:List<Double>,
    val listOfMonths:List<String>,
    val listOfBarData:List<BarDataCard>,
)
