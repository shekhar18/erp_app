package com.techcognics.erpapp.domain.usecase

import com.techcognics.erpapp.domain.repository.UserSessionRepository
import jakarta.inject.Inject

class ClearSessionUseCase @Inject constructor(
    private val repository: UserSessionRepository
) {
    suspend operator fun invoke() = repository.clearSession()
}