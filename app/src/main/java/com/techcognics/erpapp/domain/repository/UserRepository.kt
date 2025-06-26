package com.techcognics.erpapp.domain.repository

import com.techcognics.erpapp.data.login_data.LoginRequest
import com.techcognics.erpapp.data.login_data.LoginResponse
import com.techcognics.erpapp.data.profile_data.UserProfileResponse
import com.techcognics.erpapp.data.registration_data.RegistrationRequest
import com.techcognics.erpapp.data.registration_data.RegistrationResponse

interface UserRepository {
    suspend fun login(request: LoginRequest): LoginResponse
    suspend fun doRegistration(registrationRequest: RegistrationRequest): RegistrationResponse

    suspend fun getUserProfile(token: String): UserProfileResponse
}