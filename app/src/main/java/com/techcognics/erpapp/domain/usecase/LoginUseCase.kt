package com.techcognics.erpapp.domain.usecase

import com.techcognics.erpapp.data.login_data.LoginRequest
import com.techcognics.erpapp.data.login_data.LoginResponse
import com.techcognics.erpapp.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(val repository: UserRepository) {
    suspend operator fun invoke(
        userId: String,
        password: String,
        rememberCheck: Boolean
    ): LoginResponse {

       return repository.login(
           LoginRequest(
                userid = userId,
                userPassword = password,
                remember = rememberCheck
            )
        )
    }
}