package com.techcognics.erpapp.data.repository

import com.techcognics.erpapp.data.CityResponse
import com.techcognics.erpapp.data.ControlAccountDetailsResponse
import com.techcognics.erpapp.data.CountryResponse
import com.techcognics.erpapp.data.CurrencyResponse
import com.techcognics.erpapp.data.IndustryResponse
import com.techcognics.erpapp.data.LeadSourceResponse
import com.techcognics.erpapp.data.PaymentTermsResponse
import com.techcognics.erpapp.data.SalesTeamResponse
import com.techcognics.erpapp.data.StateResponse
import com.techcognics.erpapp.data.network.api_service.AppApiService
import com.techcognics.erpapp.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(val appApiService: AppApiService) : AppRepository {
    override suspend fun getCountry(token: String): List<CountryResponse> {
        return appApiService.country(token)
    }

    override suspend fun getState(token: String): List<StateResponse> {
        return appApiService.state(token)
    }

    override suspend fun getCity(token: String): List<CityResponse> {
        return appApiService.city(token)
    }

    override suspend fun getLeadSource(token: String): List<LeadSourceResponse> {
        return appApiService.leadSource(token)
    }

    override suspend fun getBillingCurrency(token: String): List<CurrencyResponse> {
        return appApiService.currency(token)
    }

    override suspend fun getTeamAndCondition(token: String): List<PaymentTermsResponse> {
        return appApiService.paymentConditions(token)
    }

    override suspend fun getAccountControl(token: String): List<ControlAccountDetailsResponse> {
        return appApiService.controlAccount(token)
    }

    override suspend fun getSalesTeam(token: String): List<SalesTeamResponse> {
        return appApiService.salseTeam(token)
    }

    override suspend fun getIndustry(token: String): List<IndustryResponse> {
        return appApiService.industry(token)
    }

}