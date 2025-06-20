package com.techcognics.erpapp.data.login_data

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("username")
    val userid:String,
    @SerializedName("password")
    val userPassword:String,
    @SerializedName("rememberMe")
    val remember: Boolean=false
)
