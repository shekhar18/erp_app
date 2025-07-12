package com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase

import com.techcognics.erpapp.data.sales_dashboard_data.FetchAllSalesInvoiceByQuarterlyResponse
import com.techcognics.erpapp.domain.repository.SalseRepository
import javax.inject.Inject

class GetFetchAllSalesInvoiceByQuarterlyUseCase @Inject constructor(val salseRepository: SalseRepository) {
    suspend operator fun invoke(
        token: String,
        startDate: String,
        endDate: String
    ): List<FetchAllSalesInvoiceByQuarterlyResponse> {
        return salseRepository.fetchAllSalesInvoiceByQuarterly(
            token = token,
            startDate = startDate,
            endDate = endDate
        )
    }
}