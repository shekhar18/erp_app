package com.techcognics.erpapp.domain.usecase

import com.techcognics.erpapp.data.registration_data.RegistrationRequest
import com.techcognics.erpapp.data.registration_data.RegistrationResponse
import com.techcognics.erpapp.data.repositorys.UserRepository
import jakarta.inject.Inject

class RegistrationUseCase @Inject constructor(val repository: UserRepository) {

    suspend operator fun invoke(
        email: String,
        password: String,
        companyName: String,
        contactPerson: String,
        countryName: String,
        mobileNumber: String,


        ): RegistrationResponse {
        return repository.doRegistration(
            RegistrationRequest(
                email = email,
                password = password,
                companyName = companyName,
                contactPerson = contactPerson,
                country = countryName,
                mobileNumber = mobileNumber
            )
        )
    }
}