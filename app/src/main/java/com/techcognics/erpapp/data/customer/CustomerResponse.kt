package com.techcognics.erpapp.data.customer

data class CustomerResponse(
    val id: Int,
    val bpcode: String,
    val bpCompanyName: String,
    val contactPerson: String,
    val mobileNumber: String,
    val address: String
)
