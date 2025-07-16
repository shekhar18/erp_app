package com.techcognics.erpapp.domain.usecase

import com.techcognics.erpapp.domain.repository.CustomerRepository
import javax.inject.Inject

class AddCustomerUseCase @Inject constructor(private val repository: CustomerRepository) {
    suspend operator fun invoke(customer: com.techcognics.erpapp.data.model.Customer) = repository.addCustomer(customer)
}