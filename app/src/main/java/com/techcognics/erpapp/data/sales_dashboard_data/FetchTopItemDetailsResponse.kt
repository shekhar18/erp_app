package com.techcognics.erpapp.data.sales_dashboard_data

import com.google.gson.annotations.SerializedName

data class FetchTopItemDetailsResponse(
    @SerializedName("itemCode") val itemCode: String?,
    @SerializedName("itemDescription") val itemDescription: String?,
    @SerializedName("totalGrossAmount") val totalGrossAmount: Double?
)
