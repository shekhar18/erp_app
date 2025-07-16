package com.techcognics.erpapp.data.profile_data

import com.google.gson.annotations.SerializedName

data class UserProfileResponse(
    @SerializedName("id") val id: Int,

    @SerializedName("login") val login: String,

    @SerializedName("firstName") val firstName: String,

    @SerializedName("lastName") val lastName: String,

    @SerializedName("email") val email: String,

    @SerializedName("imageUrl") val imageUrl: String,

    @SerializedName("activated") val activated: Boolean,

    @SerializedName("langKey") val langKey: Boolean,

    @SerializedName("createdBy") val createdBy: String,

    @SerializedName("lastModifiedBy") val lastModifiedBy: String,

    @SerializedName("lastModifiedDate") val lastModifiedDate: String,

    @SerializedName("authorities") val authorities: List<String>,


    )