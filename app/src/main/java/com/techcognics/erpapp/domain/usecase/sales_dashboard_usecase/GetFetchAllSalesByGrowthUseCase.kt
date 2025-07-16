package com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase

import android.util.Log
import com.techcognics.erpapp.data.sales_dashboard_data.FetchAllSalesByGrowthResponse
import com.techcognics.erpapp.domain.repository.SalseRepository
import javax.inject.Inject

class GetFetchAllSalesByGrowthUseCase @Inject constructor(val salesRepository: SalseRepository) {
    suspend operator fun invoke(
        token: String, startDate: String, endDate: String
    ): List<FetchAllSalesByGrowthResponse> {
        Log.d("REPO", "token:${token} \n startDate:${startDate}  \n endDate:${endDate}")
        return salesRepository.fetchAllSalesByGrowth(
            token = token, startDate = startDate, endDate = endDate
        )
    }

}