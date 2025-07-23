package com.techcognics.erpapp.data

import com.google.gson.annotations.SerializedName

data class PaymentTermsResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("termsCategory") val termsCategory: String,
    @SerializedName("termsDescription") val termsDescription: String,
    @SerializedName("activeFlag") val activeFlag: Boolean,

    )
