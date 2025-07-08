package com.techcognics.erpapp.data.sales_dashboard_data

import com.google.gson.annotations.SerializedName

data class FiscalYearOfSalesResponse(
    @SerializedName("activeFlag")
    val activeFlag: String,
    @SerializedName("id")
    val id:Int,
    @SerializedName("fiscalYear")
    val fiscalYear: Int
)