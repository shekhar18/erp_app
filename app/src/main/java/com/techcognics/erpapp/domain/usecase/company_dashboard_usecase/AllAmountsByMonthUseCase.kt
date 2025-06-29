package com.techcognics.erpapp.domain.usecase.company_dashboard_usecase

import com.techcognics.erpapp.domain.repository.UserRepository
import javax.inject.Inject

class AllAmountsByMonthUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke() {

    }
}