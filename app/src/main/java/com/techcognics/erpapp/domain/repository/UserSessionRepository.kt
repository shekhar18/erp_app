package com.techcognics.erpapp.domain.repository

import com.techcognics.erpapp.data.profile_data.UserProfileResponse
import kotlinx.coroutines.flow.Flow

interface UserSessionRepository {
    suspend fun saveToken(token: String)
    suspend fun saveUserRoles(userRoleList:List<String>)
    suspend fun getUserRoles():List<String>
    fun getToken(): Flow<String?>
    suspend fun clearSession()
    suspend fun saveUserDetails(userDetails: UserProfileResponse)
    suspend fun getUserDetails(): UserProfileResponse?
}