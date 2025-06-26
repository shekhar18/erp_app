package com.techcognics.erpapp.data.registration_data

import com.google.gson.annotations.SerializedName

data class RegistrationRequest(
    @SerializedName("email") val email: String,

    @SerializedName("password") val password: String,

    @SerializedName("companyName") val companyName: String,

    @SerializedName("contactPerson") val contactPerson: String,

    @SerializedName("country") val country: String,

    @SerializedName("mobileNumber") val mobileNumber: String,
)


