package com.techcognics.erpapp.domain.repository


import com.techcognics.erpapp.data.model.Customer

interface CustomerRepository {
    suspend fun getCustomers(): List<Customer>
    suspend fun addCustomer(customer: Customer): Customer

}
