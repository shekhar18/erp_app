package com.techcognics.erpapp.domain.usecase

import com.techcognics.erpapp.data.ControlAccountDetailsResponse
import com.techcognics.erpapp.domain.repository.AppRepository
import jakarta.inject.Inject

class RetrieveAccountControlUseCase @Inject constructor(val repository: AppRepository) {
    suspend operator fun invoke(token: String): List<ControlAccountDetailsResponse> {
        return repository.getAccountControl(token)
    }

}