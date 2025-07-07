package com.techcognics.erpapp.domain.repository

import com.techcognics.erpapp.data.company_dashboard_data.AllAmountByMonthListResponse
import com.techcognics.erpapp.data.company_dashboard_data.AllTotalAmountListResponse
import com.techcognics.erpapp.data.company_dashboard_data.AllTotalAmountResponse
import com.techcognics.erpapp.data.company_dashboard_data.AmountsByMonthListResponse
import com.techcognics.erpapp.data.company_dashboard_data.AmountsByMonthResponse
import com.techcognics.erpapp.data.company_dashboard_data.SalesInvoiceByMonthResponse
import com.techcognics.erpapp.data.company_dashboard_data.TotalIncomeAmountResponse

interface CompanyRepository {

    suspend fun getAllSalesInvoiceByMonthUseCase(
        token: String, docNo: String, pDocNo: String, startDate: String, endDate: String
    ): List<SalesInvoiceByMonthResponse>

    suspend fun getAmountsByMonth(
        token: String, docNo: String, startDate: String, endDate: String
    ): List<AmountsByMonthResponse>

    suspend fun getTotalIncomeAmount(
        token: String, startDate: String, endDate: String
    ): List<TotalIncomeAmountResponse>

    suspend fun getAllAmountsByMonth(
        token: String, docNo: String, startDate: String, endDate: String
    ): AllAmountByMonthListResponse

    suspend fun getAllTotalAmounts(
        token: String,
        docNo: String,
        startDate: String,
        endDate: String
    ): List<AllTotalAmountResponse>
}





