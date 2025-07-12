package com.techcognics.erpapp.data.user_roles

import com.google.gson.annotations.SerializedName

data class MenuResponseItem(
    @SerializedName("name") val name: String?,
    @SerializedName("authorities") val authorities: List<String>? = emptyList<String>(),
    @SerializedName("children") val children: List<Children>? = emptyList<Children>(),
    @SerializedName("code") val code: String?,
    @SerializedName("icon") val icon: String?,
    @SerializedName("toolTip") val toolTip: String?,
    @SerializedName("description") val description: String?
)