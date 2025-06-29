package com.techcognics.erpapp.domain.usecase.company_dashboard_usecase

import com.techcognics.erpapp.data.company_dashboard_data.SalesInvoiceByMonthListResponse
import com.techcognics.erpapp.data.company_dashboard_data.SalesInvoiceByMonthResponse
import com.techcognics.erpapp.domain.repository.CompanyRepository
import javax.inject.Inject

class AllSalesInvoiceByMonthUseCase @Inject constructor(private val repository: CompanyRepository) {

    suspend operator fun invoke(
        token: String, docNo: String, startDate: String, endDate: String
    ): List<SalesInvoiceByMonthResponse> {
        return repository.getAllSalesInvoiceByMonthUseCase(
            token = token, docNo = docNo, pDocNo = docNo, startDate = startDate, endDate = endDate
        )
    }


}