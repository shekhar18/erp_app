package com.techcognics.erpapp.data.sales_dashboard_data

import com.google.gson.annotations.SerializedName

data class FetchAllSalesByGrowthResponse(
    @SerializedName("month") val month: String?,
    @SerializedName("totalAmount") val totalAmount: Double?,
    @SerializedName("percentage") val percentage: Double?,
)
