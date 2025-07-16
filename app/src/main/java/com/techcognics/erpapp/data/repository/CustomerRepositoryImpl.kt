package com.techcognics.erpapp.data.repository

import com.techcognics.erpapp.data.model.Customer
import com.techcognics.erpapp.data.network.api_service.AppApiService
import com.techcognics.erpapp.domain.repository.CustomerRepository
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(
    private val apiService: AppApiService
) : CustomerRepository {
    override suspend fun getCustomers(): List<Customer> {
        return apiService.getCustomers()
    }

    override suspend fun addCustomer(customer: Customer):
            Customer =  apiService.addCustomer(customer)

    }

