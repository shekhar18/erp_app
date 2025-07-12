package com.techcognics.erpapp.data.user_roles

import com.google.gson.annotations.SerializedName

data class Children(
    @SerializedName("authorities") val authorities: List<String>? = emptyList<String>(),
    @SerializedName("code") val code: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("iconName") val icon: String?,
    @SerializedName("toolTip") val toolTip: String?
)