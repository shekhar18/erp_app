package com.techcognics.erpapp.data

import com.google.gson.annotations.SerializedName

data class IndustryResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("activeFlag") val activeFlag: Boolean,
    @SerializedName("name") val industryName: String,
    @SerializedName("code") val industryCode: String
)
