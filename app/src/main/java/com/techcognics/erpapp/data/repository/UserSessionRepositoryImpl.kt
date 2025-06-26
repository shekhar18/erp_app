package com.techcognics.erpapp.data.repository

import com.techcognics.erpapp.data.session.SessionManager
import com.techcognics.erpapp.domain.repository.UserSessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserSessionRepositoryImpl @Inject constructor(
    private val sessionManager: SessionManager
): UserSessionRepository {
    override suspend fun saveToken(token: String) = sessionManager.saveAuthToken(token)
    override fun getToken(): Flow<String?> = sessionManager.getAuthToken()
    override suspend fun clearSession() = sessionManager.clearSession()
}