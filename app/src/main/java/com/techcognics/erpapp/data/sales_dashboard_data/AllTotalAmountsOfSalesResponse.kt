package com.techcognics.erpapp.data.sales_dashboard_data

import com.google.gson.annotations.SerializedName

data class AllTotalAmountsOfSalesResponse(
    val displayName: String,
    val order:Int,
    @SerializedName("accountName") val accountName: String,
    @SerializedName("totalAmount") val totalAmount: Double,
    @SerializedName("totalCount") val totalCount: Int
)