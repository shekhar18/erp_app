package com.techcognics.erpapp.domain.usecase

import com.techcognics.erpapp.domain.repository.UserSessionRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetTokenUseCase @Inject constructor(
    private val repository: UserSessionRepository
) {
    operator fun invoke(): Flow<String?> = repository.getToken()
}