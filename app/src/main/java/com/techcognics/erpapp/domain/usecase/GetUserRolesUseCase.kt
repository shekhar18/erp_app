package com.techcognics.erpapp.domain.usecase

import com.techcognics.erpapp.data.user_roles.MenuResponseItem
import com.techcognics.erpapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUserRolesUseCase @Inject constructor(val userRepository: UserRepository) {

    suspend operator fun invoke(token:String):List<MenuResponseItem>{
        return userRepository.getUserRoles(token=token)
    }
}