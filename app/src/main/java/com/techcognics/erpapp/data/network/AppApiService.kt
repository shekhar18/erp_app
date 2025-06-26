package com.techcognics.erpapp.data.network

import com.techcognics.erpapp.data.login_data.LoginRequest
import com.techcognics.erpapp.data.login_data.LoginResponse
import com.techcognics.erpapp.data.profile_data.UserProfileResponse
import com.techcognics.erpapp.data.registration_data.RegistrationRequest
import com.techcognics.erpapp.data.registration_data.RegistrationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AppApiService {
    @POST("authenticate")
    suspend fun getAuthenticate(@Body requestBody: LoginRequest): LoginResponse

    @POST("register")
    suspend fun getCreateAccount(@Body requestBody: RegistrationRequest): RegistrationResponse

    @GET("account")
    suspend fun getUserProfile(@Header("Authorization") token: String): UserProfileResponse
}