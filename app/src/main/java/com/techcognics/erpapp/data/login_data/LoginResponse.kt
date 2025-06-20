package com.techcognics.erpapp.data.login_data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class LoginResponse(
    @SerializedName("id_token")
    val tokenId:String
)
