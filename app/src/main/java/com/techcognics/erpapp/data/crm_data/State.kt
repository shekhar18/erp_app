package com.techcognics.erpapp.data.crm_data

import com.google.gson.annotations.SerializedName

data class State(
    @SerializedName("id")val id: Int,
    @SerializedName("code")val name: String,
    @SerializedName("name")val uf: String,
    @SerializedName("refId")val refId: Int
)
