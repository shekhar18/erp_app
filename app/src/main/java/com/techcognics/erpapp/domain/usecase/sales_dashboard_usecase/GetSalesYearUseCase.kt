package com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase

import com.techcognics.erpapp.data.sales_dashboard_data.FiscalYearOfSalesResponse
import com.techcognics.erpapp.domain.repository.SalseRepository
import javax.inject.Inject

class GetSalesYearUseCase @Inject constructor(val salseRepository: SalseRepository) {

    suspend operator fun invoke(token: String): List<FiscalYearOfSalesResponse> {
        return salseRepository.getYears(token = token)
    }
}