package com.techcognics.erpapp.domain.repository

import com.techcognics.erpapp.data.sales_dashboard_data.AllTotalAmountsOfSalesResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FiscalYearOfSalesResponse
import com.techcognics.erpapp.data.sales_dashboard_data.SalesInvoiceByYearResponse
import com.techcognics.erpapp.data.sales_dashboard_data.StateWiseSalesInvoiceDetailsResponse

interface SalseRepository {


    suspend fun getYears(token: String): List<FiscalYearOfSalesResponse>
    suspend fun fetchAllTotalAmount(
        token: String, startDate: String, endDate: String
    ): List<AllTotalAmountsOfSalesResponse>

    suspend fun fetchSalseInvoiceByYear(
        token: String, startDate: String, endDate: String
    ): List<SalesInvoiceByYearResponse>


    suspend fun fetchStateWiseSalesInvoiceDetails(
        token: String, startDate: String, endDate: String
    ): List<StateWiseSalesInvoiceDetailsResponse>


}