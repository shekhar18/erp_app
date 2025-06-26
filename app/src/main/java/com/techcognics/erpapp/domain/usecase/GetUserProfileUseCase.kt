package com.techcognics.erpapp.domain.usecase

import com.techcognics.erpapp.data.profile_data.UserProfileResponse
import com.techcognics.erpapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(val userRepository: UserRepository) {

    suspend operator fun invoke(token: String): UserProfileResponse {

        return userRepository.getUserProfile(token)

    }

}