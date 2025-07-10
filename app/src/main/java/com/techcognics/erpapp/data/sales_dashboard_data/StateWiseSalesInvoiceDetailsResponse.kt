package com.techcognics.erpapp.data.sales_dashboard_data

import androidx.compose.ui.graphics.Color
import com.techcognics.erpapp.data.MainData

data class StateWiseSalesInvoiceDetailsResponse(
    val stateName: String, val percentage: Double, val color: Color

): MainData
