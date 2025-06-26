package com.techcognics.erpapp.domain.usecase

import com.techcognics.erpapp.domain.repository.UserSessionRepository
import jakarta.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val repository: UserSessionRepository
) {
    suspend operator fun invoke(token: String) = repository.saveToken(token)
}