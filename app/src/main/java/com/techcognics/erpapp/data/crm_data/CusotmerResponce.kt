package com.techcognics.erpapp.data.crm_data

import com.google.gson.annotations.SerializedName

data class CusotmerResponce(
    @SerializedName("activeFlag") val activeFlag :Boolean,
    @SerializedName("id") val id :Int,
    @SerializedName("bpCode") val bpCode :String,
    @SerializedName("bpCompanyName") val bpCompanyName :String,
    @SerializedName("category") val category :String,
    @SerializedName("country") val country :Country,
    @SerializedName("state") val state :State,
    @SerializedName("city") val city :City,
    @SerializedName("address") val address :String,
    @SerializedName("pinCode") val pinCode :String,
    @SerializedName("mobileNo") val mobileNo :String,
    @SerializedName("emailId") val emailId :String,
    @SerializedName("gstNo") val gstNo :String,
    @SerializedName("contactPerson") val contactPerson :String,


)
