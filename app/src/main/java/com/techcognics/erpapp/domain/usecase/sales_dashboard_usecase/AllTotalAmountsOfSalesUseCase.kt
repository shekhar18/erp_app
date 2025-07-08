package com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase

import com.techcognics.erpapp.data.sales_dashboard_data.AllTotalAmountsOfSalesResponse
import com.techcognics.erpapp.domain.repository.SalseRepository
import javax.inject.Inject

class AllTotalAmountsOfSalesUseCase @Inject constructor(val salseRepository: SalseRepository) {

    suspend operator fun invoke(
        toke: String, startDate: String, endDate: String
    ): List<AllTotalAmountsOfSalesResponse> {
        return salseRepository.fetchAllTotalAmount(
            token = toke,
            startDate = startDate,
            endDate = endDate
        )
    }
}