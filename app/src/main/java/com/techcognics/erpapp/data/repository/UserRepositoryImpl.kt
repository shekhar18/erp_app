package com.techcognics.erpapp.data.repository

import android.util.Log
import com.techcognics.erpapp.data.login_data.LoginRequest
import com.techcognics.erpapp.data.login_data.LoginResponse
import com.techcognics.erpapp.data.network.AppApiService
import com.techcognics.erpapp.data.profile_data.UserProfileResponse
import com.techcognics.erpapp.data.registration_data.RegistrationRequest
import com.techcognics.erpapp.data.registration_data.RegistrationResponse
import com.techcognics.erpapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val appApiService: AppApiService) : UserRepository {
    override suspend fun login(
        loginRequest: LoginRequest
    ): LoginResponse {
        Log.d("UserRepo", "UserRepo")
        return appApiService.getAuthenticate(loginRequest)
    }

    override suspend fun doRegistration(registrationRequest: RegistrationRequest): RegistrationResponse {
        return appApiService.getCreateAccount(registrationRequest)
    }

    override suspend fun getUserProfile(token: String): UserProfileResponse {
        return appApiService.getUserProfile(token = token)
    }
}