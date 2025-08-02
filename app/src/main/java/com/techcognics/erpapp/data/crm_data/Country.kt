package com.techcognics.erpapp.data.crm_data

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String,
    @SerializedName("activeFlag") val activeFlag: Boolean,
    @SerializedName("id") val id: Int
)
