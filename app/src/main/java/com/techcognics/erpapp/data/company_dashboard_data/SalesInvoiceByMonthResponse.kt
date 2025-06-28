package com.techcognics.erpapp.data.company_dashboard_data

import com.google.gson.annotations.SerializedName

data class SalesInvoiceByMonthResponse(
    @SerializedName("month")
    val month:String,
    @SerializedName("totalAmount")
    val totalAmount:String,
    @SerializedName("percentage")
    val percentage:String,
)
