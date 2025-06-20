package com.techcognics.erpapp.data.repositorys

import android.util.Log
import com.techcognics.erpapp.data.login_data.LoginRequest
import com.techcognics.erpapp.data.login_data.LoginResponse
import com.techcognics.erpapp.data.network.AppApiService
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val appApiService: AppApiService): UserRepository {
    override suspend fun login(
        loginRequest: LoginRequest
    ): LoginResponse {
        Log.d("UserRepo","UserRepo")
       return appApiService.getAuthenticate(loginRequest)
    }
}