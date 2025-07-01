package com.techcognics.erpapp.domain.usecase.company_dashboard_usecase

import com.techcognics.erpapp.data.company_dashboard_data.AmountsByMonthResponse
import com.techcognics.erpapp.domain.repository.CompanyRepository
import com.techcognics.erpapp.domain.repository.UserRepository
import javax.inject.Inject

class AmountsByMonthUseCase @Inject constructor(private val repository: CompanyRepository) {
    suspend operator fun invoke( token: String,
                                 docNo: String,
                                 startDate: String,
                                 endDate: String):List<AmountsByMonthResponse>{
            return repository.getAmountsByMonth(token = token, docNo = docNo, startDate = startDate, endDate = endDate)
    }
}