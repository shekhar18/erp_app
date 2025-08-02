package com.techcognics.erpapp.domain.usecase

import com.techcognics.erpapp.domain.repository.AppRepository
import javax.inject.Inject

class GetBusinessPartnerUseCase @Inject constructor(val appRepository: AppRepository) {
    suspend operator fun invoke(token: String) = appRepository.getCustomers(token)
}