package com.techcognics.erpapp.data

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("code") val countryCode: String,
    @SerializedName("name") val countryName: String,
    @SerializedName("id") val id: Int,
    @SerializedName("activeFlag") val activeFlag: Boolean
)
