package com.techcognics.erpapp.domain.usecase

import android.util.Log
import com.techcognics.erpapp.data.profile_data.UserProfileResponse
import com.techcognics.erpapp.domain.repository.UserSessionRepository
import javax.inject.Inject

class GetSavedUserDetailsUseCase @Inject constructor(val sessionRepository: UserSessionRepository) {
    suspend operator fun invoke(): UserProfileResponse? {
        val userDetails = sessionRepository.getUserDetails()
        Log.d("REPO", userDetails?.login.toString())
        return userDetails
    }
}