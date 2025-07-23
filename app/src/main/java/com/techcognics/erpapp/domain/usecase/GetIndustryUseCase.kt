package com.techcognics.erpapp.domain.usecase

import com.techcognics.erpapp.data.IndustryResponse
import com.techcognics.erpapp.domain.repository.AppRepository
import jakarta.inject.Inject

class GetIndustryUseCase @Inject constructor(val repository: AppRepository) {
    suspend operator fun invoke(token: String): List<IndustryResponse> =
        repository.getIndustry(token)
}