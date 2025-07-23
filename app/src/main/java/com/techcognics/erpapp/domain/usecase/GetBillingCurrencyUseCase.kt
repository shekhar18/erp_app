package com.techcognics.erpapp.domain.usecase

import com.techcognics.erpapp.domain.repository.AppRepository
import jakarta.inject.Inject

class GetBillingCurrencyUseCase @Inject constructor(val repository: AppRepository) {
    suspend operator fun invoke(token: String) = repository.getBillingCurrency(token)

}