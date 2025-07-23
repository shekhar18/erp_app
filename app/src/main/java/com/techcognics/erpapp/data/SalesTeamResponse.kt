package com.techcognics.erpapp.data

import com.google.gson.annotations.SerializedName

data class SalesTeamResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("activeFlag") val activeFlag: Boolean,
    @SerializedName("salesCode") val salesCode: String,
    @SerializedName("name") val name: String,
    @SerializedName("areaCode") val code: String,
    @SerializedName("email") val email: String,
    @SerializedName("mobileNumber") val mobileNumber: String,
)
