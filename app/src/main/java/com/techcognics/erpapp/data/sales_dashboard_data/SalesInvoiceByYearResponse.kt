package com.techcognics.erpapp.data.sales_dashboard_data

import com.google.gson.annotations.SerializedName

data class SalesInvoiceByYearResponse(
    @SerializedName("year")
    val years:String,
    @SerializedName("totalAmount")
    val totalAmount: Double,
    @SerializedName("percentage")
    val percentage: Double
)
