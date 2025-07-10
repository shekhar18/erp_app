package com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase

import com.techcognics.erpapp.data.sales_dashboard_data.FetchItemGroupDetailsPercentageResponse
import com.techcognics.erpapp.domain.repository.SalseRepository
import jakarta.inject.Inject

class FetchItemGroupDetailsPercentageUseCase @Inject constructor(val salseRepository: SalseRepository) {

    suspend operator fun invoke(
        token: String, startDate: String, endDate: String
    ): List<FetchItemGroupDetailsPercentageResponse> {
        return salseRepository.fetchItemGroupDetailsPercentage(
            token = token, startDate = startDate, endDate = endDate
        )
    }
}