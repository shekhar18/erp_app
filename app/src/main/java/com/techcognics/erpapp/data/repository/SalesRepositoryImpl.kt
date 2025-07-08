package com.techcognics.erpapp.data.repository

import com.techcognics.erpapp.data.network.api_service.AppApiService
import com.techcognics.erpapp.data.sales_dashboard_data.AllTotalAmountsOfSalesResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FiscalYearOfSalesResponse
import com.techcognics.erpapp.data.sales_dashboard_data.SalesInvoiceByYearResponse
import com.techcognics.erpapp.domain.repository.SalseRepository
import javax.inject.Inject

class SalesRepositoryImpl @Inject constructor(val appApiService: AppApiService) : SalseRepository {
    override suspend fun getYears(token: String): List<FiscalYearOfSalesResponse> {
        return appApiService.getYearsForSales(token)
    }

    override suspend fun fetchAllTotalAmount(
        token: String, startDate: String, endDate: String
    ): List<AllTotalAmountsOfSalesResponse> {
        return appApiService.getFetchAllTotalAmountsOfSales(
            token = token,
            startDate = startDate,
            endDate = endDate
        )
    }

    override suspend fun fetchSalseInvoiceByYear(
        token: String, startDate: String, endDate: String
    ): List<SalesInvoiceByYearResponse> {
        return appApiService.getFetchSalseInvoiceByYear(
            token = token,
            startDate = startDate,
            endDate = endDate
        )
    }
}