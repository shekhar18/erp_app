package com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase

import com.techcognics.erpapp.data.sales_dashboard_data.SalesInvoiceByYearResponse
import com.techcognics.erpapp.domain.repository.SalseRepository
import javax.inject.Inject

class GetSalseInvoiceByYearUseCase @Inject constructor(val salesRepository: SalseRepository) {

    suspend operator fun invoke(
        token: String, startDate: String, endDate: String
    ): List<SalesInvoiceByYearResponse> {
        return salesRepository.fetchSalseInvoiceByYear(
            token = token,
            startDate = startDate,
            endDate = endDate
        )
    }
}