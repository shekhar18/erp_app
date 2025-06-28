package com.techcognics.erpapp.data.company_dashboard_data

import com.google.gson.annotations.SerializedName

data class TotalIncomeAmountResponse(
    @SerializedName("month")
    val month:String,
    @SerializedName("totalIncome")
    val totalIncome:String,
    @SerializedName("totalExpense")
    val totalExpense:String,
)
