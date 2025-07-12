package com.techcognics.erpapp.data.repository

import com.techcognics.erpapp.data.network.api_service.AppApiService
import com.techcognics.erpapp.data.sales_dashboard_data.AllTotalAmountsOfSalesResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FetchAllSalesInvoiceByQuarterlyResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FetchItemGroupDetailsPercentageResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FetchSalesByTopCustomerResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FetchTopItemDetailsResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FiscalYearOfSalesResponse
import com.techcognics.erpapp.data.sales_dashboard_data.SalesInvoiceByYearResponse
import com.techcognics.erpapp.data.sales_dashboard_data.StateWiseSalesInvoiceDetailsResponse
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
            token = token, startDate = startDate, endDate = endDate
        )
    }

    override suspend fun fetchSalseInvoiceByYear(
        token: String, startDate: String, endDate: String
    ): List<SalesInvoiceByYearResponse> {
        return appApiService.getFetchSalseInvoiceByYear(
            token = token, startDate = startDate, endDate = endDate
        )
    }

    override suspend fun fetchStateWiseSalesInvoiceDetails(
        token: String, startDate: String, endDate: String
    ): List<StateWiseSalesInvoiceDetailsResponse> {
        return appApiService.getFetchStateWiseSalesInvoiceDetails(
            token = token, startDate = startDate, endDate = endDate
        )
    }

    override suspend fun fetchItemGroupDetailsPercentage(
        token: String, startDate: String, endDate: String
    ): List<FetchItemGroupDetailsPercentageResponse> {
        return appApiService.getFetchItemGroupDetailsPercentage(
            token = token, startDate = startDate, endDate = endDate
        )
    }

    override suspend fun fetchSalesByTopCustomer(
        token: String, startDate: String, endDate: String
    ): List<FetchSalesByTopCustomerResponse> {
        return appApiService.getFetchSalesByTopCustomer(
            token = token, startDate = startDate, endDate = endDate
        )
    }

    override suspend fun fetchTopSalesDetails(
        token: String, startDate: String, endDate: String
    ): List<FetchTopItemDetailsResponse> {
        return appApiService.getFetchTopSalesDetailsByTopCustomer(
            token = token, startDate = startDate, endDate = endDate
        )
    }

    override suspend fun fetchAllSalesInvoiceByQuarterly(
        token: String,
        startDate: String,
        endDate: String
    ): List<FetchAllSalesInvoiceByQuarterlyResponse> {
        return appApiService.getFetchAllSalesInvoiceByQuarterly(
            token = token, startDate = startDate, endDate = endDate
        )
    }


}