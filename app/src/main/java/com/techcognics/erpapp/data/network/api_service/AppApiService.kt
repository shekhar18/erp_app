package com.techcognics.erpapp.data.network.api_service

import com.techcognics.erpapp.data.company_dashboard_data.AllAmountByMonthListResponse
import com.techcognics.erpapp.data.company_dashboard_data.AllTotalAmountResponse
import com.techcognics.erpapp.data.company_dashboard_data.AmountsByMonthResponse
import com.techcognics.erpapp.data.company_dashboard_data.SalesInvoiceByMonthResponse
import com.techcognics.erpapp.data.company_dashboard_data.TotalIncomeAmountResponse
import com.techcognics.erpapp.data.login_data.LoginRequest
import com.techcognics.erpapp.data.login_data.LoginResponse
import com.techcognics.erpapp.data.model.Customer
import com.techcognics.erpapp.data.profile_data.UserProfileResponse
import com.techcognics.erpapp.data.registration_data.RegistrationRequest
import com.techcognics.erpapp.data.registration_data.RegistrationResponse
import com.techcognics.erpapp.data.sales_dashboard_data.AllTotalAmountsOfSalesResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FetchAllSalesInvoiceByQuarterlyResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FetchItemGroupDetailsPercentageResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FetchSalesByTopCustomerResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FetchTopItemDetailsResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FiscalYearOfSalesResponse
import com.techcognics.erpapp.data.sales_dashboard_data.SalesInvoiceByYearResponse
import com.techcognics.erpapp.data.sales_dashboard_data.StateWiseSalesInvoiceDetailsResponse
import com.techcognics.erpapp.data.user_roles.MenuResponseItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AppApiService {
    @POST("authenticate")
    suspend fun getAuthenticate(@Body requestBody: LoginRequest): LoginResponse

    @POST("register")
    suspend fun getCreateAccount(@Body requestBody: RegistrationRequest): RegistrationResponse

    @GET("account")
    suspend fun getUserProfile(@Header("Authorization") token: String): UserProfileResponse

    @GET("entitlements/userEntitlements")
    suspend fun getUserRole(@Header("Authorization") token: String): List<MenuResponseItem>


    //Company Dashboard
    @GET("dashboard/fetchAllSalesInvoicesByMonth/{docNo}")
    suspend fun getFetchAllSalseInvoicesByMonth(
        @Header("Authorization") token: String,
        @Path("docNo") docNo: String,
        @Query("docNo") pDocNo: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): List<SalesInvoiceByMonthResponse>

    @GET("dashboard/fetchAmountsByMonth")
    suspend fun getFetchAmountsByMonth(
        @Header("Authorization") token: String,
        @Query("docNo") pDocNo: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): List<AmountsByMonthResponse>

    @GET("dashboard/fetchTotalIncomeAmount")
    suspend fun getFetchTotalIncomeAmount(
        @Header("Authorization") token: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): List<TotalIncomeAmountResponse>


    @GET("dashboard/fetchAllAmountsByMonth")
    suspend fun getFetchAllAmountsByMonth(
        @Header("Authorization") token: String,
        @Query("docNo") pDocNo: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): AllAmountByMonthListResponse

    @GET("dashboard/fetchAllTotalAmounts")
    suspend fun getFetchAllTotalAmounts(
        @Header("Authorization") token: String,
        @Query("docNo") pDocNo: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): List<AllTotalAmountResponse>


    //sales Dashboard
    @GET("salesDashboard/fetchAllTotalAmounts")
    suspend fun getFetchAllTotalAmountsOfSales(
        @Header("Authorization") token: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): List<AllTotalAmountsOfSalesResponse>

    @GET("fiscalYear/fetchAllFiscalYears")
    suspend fun getYearsForSales(@Header("Authorization") token: String): List<FiscalYearOfSalesResponse>

    @GET("salesDashboard/fetchAllSalesInvoiceByYear")
    suspend fun getFetchSalseInvoiceByYear(
        @Header("Authorization") token: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): List<SalesInvoiceByYearResponse>

    @GET("salesDashboard/fetchStateWiseSalesInvoiceDetails")
    suspend fun getFetchStateWiseSalesInvoiceDetails(
        @Header("Authorization") token: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): List<StateWiseSalesInvoiceDetailsResponse>

    @GET("salesDashboard/fetchItemGroupDetailsPercentage")
    suspend fun getFetchItemGroupDetailsPercentage(
        @Header("Authorization") token: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): List<FetchItemGroupDetailsPercentageResponse>



    @GET("salesDashboard/fetchSalesByTopCustomer")
    suspend fun getFetchSalesByTopCustomer(
        @Header("Authorization") token: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): List<FetchSalesByTopCustomerResponse>

    @GET("salesDashboard/fetchTopItemDetails")
    suspend fun getFetchTopSalesDetailsByTopCustomer(
        @Header("Authorization") token: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): List<FetchTopItemDetailsResponse>

    @GET("salesDashboard/fetchAllSalesInvoiceByQuarterly")
    suspend fun getFetchAllSalesInvoiceByQuarterly(
        @Header("Authorization") token: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): List<FetchAllSalesInvoiceByQuarterlyResponse>

    //customer crm
    @GET("api/businessPartner/fetchBpDetailsBy/CUSTOMER")
    suspend fun getCustomers(): List<Customer>

    @POST("api/businessPartner/save")
    suspend fun addCustomer(@Body customer: Customer): Customer


}

