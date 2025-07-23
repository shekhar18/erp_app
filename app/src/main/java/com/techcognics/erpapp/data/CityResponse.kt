package com.techcognics.erpapp.data

import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("code") val cityCode: String,
    @SerializedName("name") val cityName: String,
    @SerializedName("id") val id: Int,
    @SerializedName("refId") val refId: Int,
    @SerializedName("activeFlag") val activeFlag: Boolean
)
