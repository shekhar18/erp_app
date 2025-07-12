package com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase

import com.techcognics.erpapp.data.sales_dashboard_data.FetchTopItemDetailsResponse
import com.techcognics.erpapp.domain.repository.SalseRepository
import jakarta.inject.Inject

class GetTopSalesDetailsUseCase @Inject constructor(val salesRepository: SalseRepository) {
    suspend operator fun invoke(
        token: String, startDate: String, endDate: String
    ): List<FetchTopItemDetailsResponse> {
        return salesRepository.fetchTopSalesDetails(
            token = token, startDate = startDate, endDate = endDate
        )
    }
}