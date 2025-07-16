package com.techcognics.erpapp.data.model

data class Customer(
    val id: Int? = null,
    val bpCode: String? = null,
    val bpCompanyName: String? = null,
    val category: String = "CUSTOMER",
    val address: String,
    val pincode: String? = null,
    val contactPerson: String,
    val mobileNo: String,
    val email: String,
    val city: City,
    val country: Country

)

data class City(
    val id: Int? = null,
    val code: String? = null,
    val name: String
)
data class Country(
    val code: String,
    val name: String
)