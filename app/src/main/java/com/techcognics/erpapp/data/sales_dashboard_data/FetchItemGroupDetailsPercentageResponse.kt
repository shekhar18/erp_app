package com.techcognics.erpapp.data.sales_dashboard_data

import com.google.gson.annotations.SerializedName
import com.techcognics.erpapp.data.MainData

data class FetchItemGroupDetailsPercentageResponse(
    @SerializedName("itemGroupName")
    val itemGroupName :String,
    @SerializedName("percentage")
    val percentage: Double
): MainData
