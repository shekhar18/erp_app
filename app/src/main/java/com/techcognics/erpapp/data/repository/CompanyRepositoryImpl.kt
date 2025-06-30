package com.techcognics.erpapp.data.repository

import com.techcognics.erpapp.data.company_dashboard_data.AllAmountByMonthListResponse
import com.techcognics.erpapp.data.company_dashboard_data.AllTotalAmountListResponse
import com.techcognics.erpapp.data.company_dashboard_data.AllTotalAmountResponse
import com.techcognics.erpapp.data.company_dashboard_data.AmountsByMonthListResponse
import com.techcognics.erpapp.data.company_dashboard_data.SalesInvoiceByMonthResponse
import com.techcognics.erpapp.data.company_dashboard_data.TotalIncomeAmountResponse
import com.techcognics.erpapp.data.network.api_service.AppApiService
import com.techcognics.erpapp.domain.repository.CompanyRepository
import jakarta.inject.Inject

class CompanyRepositoryImpl @Inject constructor(val appApiService: AppApiService) :
    CompanyRepository {
    override suspend fun getAllSalesInvoiceByMonthUseCase(
        token: String, docNo: String, pDocNo: String, startDate: String, endDate: String
    ): List<SalesInvoiceByMonthResponse> {
        return appApiService.getFetchAllSalseInvoicesByMonth(
            token = token,
            docNo = docNo,
            pDocNo = pDocNo,
            startDate = startDate,
            endDate = endDate,
        )
    }

    override suspend fun getAmountsByMonth(
        token: String, docNo: String, startDate: String, endDate: String
    ): AmountsByMonthListResponse {
        return appApiService.getFetchAmountsByMonth(
            token = token, pDocNo = docNo, startDate = startDate, endDate = endDate
        )
    }

    override suspend fun getTotalIncomeAmount(
        token: String, startDate: String, endDate: String
    ): List<TotalIncomeAmountResponse> {
        return appApiService.getFetchTotalIncomeAmount(
            token = token,
            startDate = startDate,
            endDate = endDate
        )
    }


    override suspend fun getAllAmountsByMonth(
        token: String, docNo: String, startDate: String, endDate: String
    ): AllAmountByMonthListResponse {
        return appApiService.getFetchAllAmountsByMonth(
            token = token, pDocNo = docNo, startDate = startDate, endDate = endDate
        )
    }

    override suspend fun getAllTotalAmounts(
        token: String, docNo: String, startDate: String, endDate: String
    ): List<AllTotalAmountResponse> {
        return appApiService.getFetchAllTotalAmounts(
            token = token, pDocNo = docNo, startDate = startDate, endDate = endDate
        )
    }
}