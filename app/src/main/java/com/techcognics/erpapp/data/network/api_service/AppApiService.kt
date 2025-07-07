package com.techcognics.erpapp.data.network.api_service

import com.techcognics.erpapp.data.company_dashboard_data.AllAmountByMonthListResponse
import com.techcognics.erpapp.data.company_dashboard_data.AllTotalAmountListResponse
import com.techcognics.erpapp.data.company_dashboard_data.AllTotalAmountResponse
import com.techcognics.erpapp.data.company_dashboard_data.AmountsByMonthListResponse
import com.techcognics.erpapp.data.company_dashboard_data.AmountsByMonthResponse
import com.techcognics.erpapp.data.company_dashboard_data.SalesInvoiceByMonthResponse
import com.techcognics.erpapp.data.company_dashboard_data.TotalIncomeAmountListResponse
import com.techcognics.erpapp.data.company_dashboard_data.TotalIncomeAmountResponse
import com.techcognics.erpapp.data.login_data.LoginRequest
import com.techcognics.erpapp.data.login_data.LoginResponse
import com.techcognics.erpapp.data.profile_data.UserProfileResponse
import com.techcognics.erpapp.data.registration_data.RegistrationRequest
import com.techcognics.erpapp.data.registration_data.RegistrationResponse
import com.techcognics.erpapp.data.user_roles.MenuResponseItem

import com.techcognics.erpapp.data.sales_dashboard_data



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
    suspend fun getUserRole(@Header("Authorization") token: String):List<MenuResponseItem>


    //Company Dashboard
    @GET("dashboard/fetchAllSalesInvoicesByMonth/{docNo}")
    suspend fun getFetchAllSalseInvoicesByMonth(
        @Header("Authorization") token: String,
        @Path("docNo") docNo: String,
        @Query("docNo") pDocNo: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): List<AllSalesInvoiceByMonthResponse>

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
    ): List<>

    //sales dashboard
    @GET("salesDashboard/fetchAllTotalAmounts")
    suspend fun getfetchAllTotalAmountSales(
        @Header("Authorization") token: String,
        @Query("docNo") pDocNo: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ):  List<>

    @GET("api/salesDashboard/fetchAllSalesInvoiceByQuarterly)
            suspend fun getfetchAllSalesInvoiceByQuarterly(
                @Header("Authorization") token: String,
                @Query("docNo") pDocNo: String,
                @Query("startDate") startDate: String,
                @Query("endDate") endDate: String
            ): List<>

    @GET("salesDashboard/fetchAllSalesInvoiceByYear")
    suspend fun getfetchAllSalesInvoiceByYear(
        @Header("Authorization") token: String,
        @Query("docNo") pDocNo: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): List<>

    @GET("salesDashboard/fetchExpectedRevenueByMonth")
    suspend fun getfetchExpectedRevenueByMonth(
        @Header("Authorization") token: String,
        @Query("docNo") pDocNo: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String

    ): List<>

    @GET("salesDashboard/fetchAllSalesByGrowth")
    suspend fun fetchAllSalesByGrowth(
        @Header("Authorization") token: String,
        @Query("docNo") pDocNo: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): List<>

    @GET("salesDashboard/fetchStateWiseSalesInvoiceDetails")
    suspend fun fetchStateWiseSalesInvoiceDetails(
        @Header("Authorization") token: String,
        @Query("docNo") pDocNo: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): List<>

}




















