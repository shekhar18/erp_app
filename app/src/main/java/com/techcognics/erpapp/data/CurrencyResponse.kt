package com.techcognics.erpapp.data

import com.google.gson.annotations.SerializedName


data class CurrencyResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("activeFlag") val activeFlag: Boolean,
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String,
)