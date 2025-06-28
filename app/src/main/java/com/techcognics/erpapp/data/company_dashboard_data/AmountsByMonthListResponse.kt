package com.techcognics.erpapp.data.company_dashboard_data

data class AmountsByMonthListResponse(
    val listOfAmountsByMonths: List<AmountsByMonthResponse> = emptyList<AmountsByMonthResponse>()
)
