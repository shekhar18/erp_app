package com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase

import com.techcognics.erpapp.data.sales_dashboard_data.FetchSalesByTopCustomerResponse
import com.techcognics.erpapp.domain.repository.SalseRepository
import javax.inject.Inject

class FetchSalesByTopCustomerUseCase @Inject constructor(val salesRepository: SalseRepository) {

    suspend operator fun invoke(
        token: String,
        startDate: String,
        endDate: String
    ): List<FetchSalesByTopCustomerResponse> {
        return salesRepository.fetchSalesByTopCustomer(
            token = token,
            startDate = startDate,
            endDate = endDate
        )
    }
}