package com.techcognics.erpapp.presentation.screens.sales_dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcognics.erpapp.data.sales_dashboard_data.AllTotalAmountsOfSalesResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FiscalYearOfSalesResponse
import com.techcognics.erpapp.data.sales_dashboard_data.SalesInvoiceByYearResponse
import com.techcognics.erpapp.domain.usecase.GetTokenUseCase
import com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase.AllTotalAmountsOfSalesUseCase
import com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase.GetSalesYearUseCase
import com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase.GetSalseInvoiceByYearUseCase
import com.techcognics.erpapp.presentation.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SalesDashboardViewModel @Inject constructor(
    private val getSalesYearUseCase: GetSalesYearUseCase,
    private val allTotalAmountsOfSaleUseCase: AllTotalAmountsOfSalesUseCase,
    private val salseInvoiceByYearUseCase: GetSalseInvoiceByYearUseCase,
    private val getTokenUseCase: GetTokenUseCase,
) : ViewModel() {

    private val _salesDashboardState = MutableLiveData<Result<Unit>>(
        Result.Idle
    )
    val salesDashboardState: LiveData<Result<Unit>> = _salesDashboardState

    private val _startDate = MutableLiveData<String>()
    val startDate: LiveData<String> = _startDate

    private val _endDate = MutableLiveData<String>()
    val endDate: LiveData<String> = _endDate


    private val _yearList: MutableLiveData<List<FiscalYearOfSalesResponse>> =
        MutableLiveData<List<FiscalYearOfSalesResponse>>()
    val yearList: LiveData<List<FiscalYearOfSalesResponse>> = _yearList

    private val _salesTilesData: MutableLiveData<List<AllTotalAmountsOfSalesResponse>> =
        MutableLiveData<List<AllTotalAmountsOfSalesResponse>>()
    val salesTilesData: LiveData<List<AllTotalAmountsOfSalesResponse>> = _salesTilesData

    private val _salesInvoiceByYearList: MutableLiveData<List<SalesInvoiceByYearResponse>> =
        MutableLiveData<List<SalesInvoiceByYearResponse>>()
    val salesInvoiceByYearList: LiveData<List<SalesInvoiceByYearResponse>> = _salesInvoiceByYearList


    fun getFiscalYear() {
        viewModelScope.launch {
            _salesDashboardState.value = Result.Loading
            withContext(Dispatchers.IO) {
                getTokenUseCase().collect { token ->

                    val yearList = getSalesYearUseCase(token = token.orEmpty())
                    _yearList.postValue(yearList)
                    withContext(Dispatchers.Main) {
                        _salesDashboardState.value = Result.Idle
                    }
                    Log.d("SALES", yearList.toString())
                }
            }

        }
    }

    fun getFetchSalesDashboardData() {
        viewModelScope.launch {
            _salesDashboardState.value = Result.Loading
            withContext(Dispatchers.IO) {
                getTokenUseCase().collect { token ->
                    val response = allTotalAmountsOfSaleUseCase(
                        toke = token.toString(),
                        startDate = startDate.value.orEmpty(),
                        endDate = endDate.value.orEmpty()
                    )
                    val mergeList = mergeSalesData(defaultList(), response)
                    _salesTilesData.postValue(mergeList)

                   val invoiceByYearList =  salseInvoiceByYearUseCase(
                        token = token.toString(),
                        startDate = startDate.value.orEmpty(),
                        endDate = endDate.value.orEmpty()
                    )

                    _salesInvoiceByYearList.postValue(invoiceByYearList)


                    Log.d("SALES", response.toString())
                    withContext(Dispatchers.Main) {
                        _salesDashboardState.value = Result.Idle
                    }

                }
            }
        }
    }


    fun updateStartDate(startDate: String) {
        _startDate.value = startDate
    }

    fun updateEndDate(endDate: String) {
        _endDate.value = endDate
    }


    fun defaultList(): List<AllTotalAmountsOfSalesResponse> {
        return listOf(
            AllTotalAmountsOfSalesResponse(
                accountName = "Sales Quotation",
                displayName = "",
                totalCount = 0,
                totalAmount = 0.0,
                order = 4
            ),
            AllTotalAmountsOfSalesResponse(
                accountName = "Sales Order",
                displayName = "Sales Order",
                totalCount = 0,
                totalAmount = 0.0,
                order = 3
            ),
            AllTotalAmountsOfSalesResponse(
                accountName = "Sales Invoice",
                displayName = "Revenue",
                totalCount = 0,
                totalAmount = 0.0,
                order = 1
            ),
            AllTotalAmountsOfSalesResponse(
                accountName = "Sales Order Challan",
                displayName = "",
                totalCount = 0,
                totalAmount = 0.0,
                order = 5
            ),
            AllTotalAmountsOfSalesResponse(
                accountName = "Expected Revenue",
                displayName = "Expected Revenue",
                totalCount = 0,
                totalAmount = 0.0,
                order = 2
            ),
        )
    }

    fun mergeSalesData(
        defaultList: List<AllTotalAmountsOfSalesResponse>,
        serverList: List<AllTotalAmountsOfSalesResponse>
    ): List<AllTotalAmountsOfSalesResponse> {
        // Create a map for fast lookup by accountName or accountCode (your call)
        val serverMap = serverList.associateBy { it.accountName } // or .accountCode

        return defaultList.map { defaultItem ->
            // Try to get server data for this accountName
            val serverItem = serverMap[defaultItem.accountName]
            if (serverItem != null) {
                defaultItem.copy(
                    totalAmount = serverItem.totalAmount ?: 0.0,
                    totalCount = serverItem.totalCount ?: 0
                )
            } else {
                defaultItem // keep default values if not found
            }
        }
    }

}