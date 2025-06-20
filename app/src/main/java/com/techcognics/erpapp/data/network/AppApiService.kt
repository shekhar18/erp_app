package com.techcognics.erpapp.data.network

import com.techcognics.erpapp.data.login_data.LoginRequest
import com.techcognics.erpapp.data.login_data.LoginResponse
import com.techcognics.erpapp.data.registration_data.RegistrationRequest
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AppApiService {
    @POST("authenticate")
    suspend fun getAuthenticate(@Body requestBody: LoginRequest): LoginResponse

    @POST("account")
    suspend fun getCreateAccount(@Body requestBody:RegistrationRequest)
}