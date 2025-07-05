package com.techcognics.erpapp.domain.usecase

import com.techcognics.erpapp.domain.repository.UserSessionRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetSaveUserRoleUseCase @Inject constructor(
    private val repository: UserSessionRepository
) {
    suspend operator fun invoke(): List<String> = repository.getUserRoles()
}