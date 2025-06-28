package com.techcognics.erpapp.data.company_dashboard_data

import com.google.gson.annotations.SerializedName

data class AllAmountByMonthResponse(
    @SerializedName("month") val month: String,
    @SerializedName("accountCode") val accountCode: String,
    @SerializedName("accountName") val accountName: String,
    @SerializedName("totalAmount") val totalAmount: String,
)
