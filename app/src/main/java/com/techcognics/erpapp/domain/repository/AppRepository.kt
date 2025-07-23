package com.techcognics.erpapp.domain.repository

import com.techcognics.erpapp.data.CityResponse
import com.techcognics.erpapp.data.ControlAccountDetailsResponse
import com.techcognics.erpapp.data.CountryResponse
import com.techcognics.erpapp.data.CurrencyResponse
import com.techcognics.erpapp.data.IndustryResponse
import com.techcognics.erpapp.data.LeadSourceResponse
import com.techcognics.erpapp.data.PaymentTermsResponse
import com.techcognics.erpapp.data.SalesTeamResponse
import com.techcognics.erpapp.data.StateResponse

interface AppRepository {
    suspend fun getCountry(
        token: String
    ): List<CountryResponse>

    suspend fun getState(
        token: String
    ): List<StateResponse>

    suspend fun getCity(
        token: String
    ): List<CityResponse>

    suspend fun getLeadSource(
        token: String
    ): List<LeadSourceResponse>

    suspend fun getBillingCurrency(token: String): List<CurrencyResponse>
    suspend fun getTeamAndCondition(token: String): List<PaymentTermsResponse>

    suspend fun getAccountControl(token: String): List<ControlAccountDetailsResponse>
    suspend fun getSalesTeam(token: String): List<SalesTeamResponse>
    suspend fun getIndustry(token: String): List<IndustryResponse>


}