package com.techcognics.erpapp.data.repositorys

import com.techcognics.erpapp.data.login_data.LoginRequest
import com.techcognics.erpapp.data.login_data.LoginResponse

interface UserRepository {
    suspend fun login( request: LoginRequest):LoginResponse
}