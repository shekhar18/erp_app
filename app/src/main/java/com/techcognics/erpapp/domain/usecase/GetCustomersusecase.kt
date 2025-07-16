package com.techcognics.erpapp.domain.usecase



import com.techcognics.erpapp.domain.repository.CustomerRepository
import javax.inject.Inject

class GetCustomersUseCase @Inject constructor(
    private val repository: CustomerRepository
) {
    suspend operator fun invoke() = repository.getCustomers()
}
