package com.techcognics.erpapp.data

import com.google.gson.annotations.SerializedName

data class ControlAccountDetailsResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("activeFlag") val activeFlag: Boolean,
    @SerializedName("controlType") val controlType: String,
    @SerializedName("customerAccountCode") val customerAccountCode: String,
)
