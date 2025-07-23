package com.techcognics.erpapp.data

import com.google.gson.annotations.SerializedName

data class StateResponse(
    @SerializedName("code") val stateCode: String,
    @SerializedName("name") val stateName: String,
    @SerializedName("id") val id: Int,
    @SerializedName("refId") val refId: Int,
    @SerializedName("activeFlag") val activeFlag: Boolean
)
