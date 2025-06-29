package com.techcognics.erpapp.domain.repository

import com.techcognics.erpapp.data.company_dashboard_data.AllAmountByMonthListResponse
import com.techcognics.erpapp.data.company_dashboard_data.AllTotalAmountListResponse
import com.techcognics.erpapp.data.company_dashboard_data.AmountsByMonthListResponse
import com.techcognics.erpapp.data.company_dashboard_data.SalesInvoiceByMonthListResponse
import com.techcognics.erpapp.data.company_dashboard_data.TotalIncomeAmountListResponse
import com.techcognics.erpapp.data.login_data.LoginRequest
import com.techcognics.erpapp.data.login_data.LoginResponse
import com.techcognics.erpapp.data.profile_data.UserProfileResponse
import com.techcognics.erpapp.data.registration_data.RegistrationRequest
import com.techcognics.erpapp.data.registration_data.RegistrationResponse

interface UserRepository {
    suspend fun login(request: LoginRequest): LoginResponse
    suspend fun doRegistration(registrationRequest: RegistrationRequest): RegistrationResponse
    suspend fun getUserProfile(token: String): UserProfileResponse


}