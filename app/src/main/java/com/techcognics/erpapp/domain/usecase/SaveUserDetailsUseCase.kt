package com.techcognics.erpapp.domain.usecase

import com.techcognics.erpapp.data.profile_data.UserProfileResponse
import com.techcognics.erpapp.domain.repository.UserSessionRepository
import javax.inject.Inject

class SaveUserDetailsUseCase @Inject constructor(val sessionRepository: UserSessionRepository) {
    suspend operator fun invoke(userDetails:UserProfileResponse) {
        return sessionRepository.saveUserDetails(userDetails)
    }
}