package com.techcognics.erpapp.data


data class CompanyStatusCard(
    val title: String = "",
    val data: List<Double> = emptyList<Double>(),
    val totalAmount: Double = 0.0,
    val percentage: Double = 0.0,
    val color: androidx.compose.ui.graphics.Color
)
