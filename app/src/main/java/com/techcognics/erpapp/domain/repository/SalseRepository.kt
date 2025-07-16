package com.techcognics.erpapp.domain.repository

import com.techcognics.erpapp.data.sales_dashboard_data.AllTotalAmountsOfSalesResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FetchAllSalesByGrowthResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FetchAllSalesInvoiceByQuarterlyResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FetchItemGroupDetailsPercentageResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FetchSalesByTopCustomerResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FetchTopItemDetailsResponse
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


    suspend fun fetchItemGroupDetailsPercentage(
        token: String, startDate: String, endDate: String
    ): List<FetchItemGroupDetailsPercentageResponse>

    suspend fun fetchSalesByTopCustomer(
        token: String, startDate: String, endDate: String
    ): List<FetchSalesByTopCustomerResponse>

    suspend fun fetchTopSalesDetails(token: String, startDate: String, endDate: String):List<FetchTopItemDetailsResponse>
    suspend fun fetchAllSalesInvoiceByQuarterly(token: String, startDate: String, endDate: String):List<FetchAllSalesInvoiceByQuarterlyResponse>
    suspend fun fetchAllSalesByGrowth(token: String, startDate: String, endDate: String):List<FetchAllSalesByGrowthResponse>
}