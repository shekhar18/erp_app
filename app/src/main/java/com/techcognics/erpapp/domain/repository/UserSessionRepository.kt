package com.techcognics.erpapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserSessionRepository {
    suspend fun saveToken(token: String)
    fun getToken(): Flow<String?>
    suspend fun clearSession()
}