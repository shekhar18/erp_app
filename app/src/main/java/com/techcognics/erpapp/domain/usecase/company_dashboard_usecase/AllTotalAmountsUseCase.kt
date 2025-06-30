package com.techcognics.erpapp.domain.usecase.company_dashboard_usecase

import com.techcognics.erpapp.data.company_dashboard_data.AllTotalAmountResponse
import com.techcognics.erpapp.domain.repository.CompanyRepository
import javax.inject.Inject

class AllTotalAmountsUseCase @Inject constructor(private val repository: CompanyRepository) {


    suspend operator fun invoke(
        token: String,
        docNo: String,
        startDate: String,
        endDate: String
    ): List<AllTotalAmountResponse> {
        return repository.getAllTotalAmounts(
            token = token.toString(),
            docNo = docNo,
            startDate = startDate,
            endDate = endDate
        )
    }
}