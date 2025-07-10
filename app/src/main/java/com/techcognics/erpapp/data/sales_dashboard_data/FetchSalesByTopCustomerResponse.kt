package com.techcognics.erpapp.data.sales_dashboard_data

data class FetchSalesByTopCustomerResponse(
    val accountName: String,
    val customerCode: String,
    val customerName: String,
    val totalAmount: Double
)
