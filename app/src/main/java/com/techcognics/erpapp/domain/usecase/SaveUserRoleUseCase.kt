package com.techcognics.erpapp.domain.usecase

import com.techcognics.erpapp.domain.repository.UserSessionRepository
import jakarta.inject.Inject

class SaveUserRoleUseCase @Inject constructor(
    private val repository: UserSessionRepository
) {
    suspend operator fun invoke(userRoleList: List<String>) = repository.saveUserRoles(userRoleList)
}