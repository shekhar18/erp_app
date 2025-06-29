package com.techcognics.erpapp.domain.usecase.company_dashboard_usecase

import com.techcognics.erpapp.data.company_dashboard_data.SalesInvoiceByMonthResponse
import com.techcognics.erpapp.data.company_dashboard_data.TotalIncomeAmountResponse
import com.techcognics.erpapp.domain.repository.CompanyRepository
import com.techcognics.erpapp.domain.repository.UserRepository
import javax.inject.Inject

class TotalIncomeAmountUseCase @Inject constructor(private val repository: CompanyRepository) {

    suspend operator fun invoke(
        token: String, startDate: String, endDate: String
    ): List<TotalIncomeAmountResponse> {
        return repository.getTotalIncomeAmount(token = token, startDate = startDate, endDate = endDate)
    }
}