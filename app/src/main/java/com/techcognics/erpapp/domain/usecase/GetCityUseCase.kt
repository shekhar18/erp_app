package com.techcognics.erpapp.domain.usecase

import com.techcognics.erpapp.domain.repository.AppRepository
import jakarta.inject.Inject

class GetCityUseCase @Inject constructor(private val appRepository: AppRepository) {
    suspend operator fun invoke(token: String) = appRepository.getCity(token)
}