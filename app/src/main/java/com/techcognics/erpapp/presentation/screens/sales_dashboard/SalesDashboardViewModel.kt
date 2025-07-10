package com.techcognics.erpapp.presentation.screens.sales_dashboard

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcognics.erpapp.data.sales_dashboard_data.AllTotalAmountsOfSalesResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FiscalYearOfSalesResponse
import com.techcognics.erpapp.data.sales_dashboard_data.SalesInvoiceByYearResponse
import com.techcognics.erpapp.data.sales_dashboard_data.StateWiseSalesInvoiceDetailsResponse
import com.techcognics.erpapp.domain.usecase.GetTokenUseCase
import com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase.AllTotalAmountsOfSalesUseCase
import com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase.FetchStateWiseSalesInvoiceDetailsUseCase
import com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase.GetSalesYearUseCase
import com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase.GetSalseInvoiceByYearUseCase
import com.techcognics.erpapp.presentation.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class SalesDashboardViewModel @Inject constructor(
    private val getSalesYearUseCase: GetSalesYearUseCase,
    private val allTotalAmountsOfSaleUseCase: AllTotalAmountsOfSalesUseCase,
    private val salseInvoiceByYearUseCase: GetSalseInvoiceByYearUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val fetchStateWiseSalesInvoiceDetailsUseCase: FetchStateWiseSalesInvoiceDetailsUseCase
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


    private val _startDateChart = MutableLiveData<String>()
    val startDateChart: LiveData<String> = _startDateChart

    private val _endDateChart = MutableLiveData<String>()
    val endDateChart: LiveData<String> = _endDateChart
    private val _stateWiseSalesInvoiceDetailList: MutableLiveData<List<StateWiseSalesInvoiceDetailsResponse>> =
        MutableLiveData<List<StateWiseSalesInvoiceDetailsResponse>>()
    val stateWiseSalesInvoiceDetailList: LiveData<List<StateWiseSalesInvoiceDetailsResponse>> =
        _stateWiseSalesInvoiceDetailList

    private val _buttonTag = MutableLiveData<String>("1Y")
    val buttonTag: LiveData<String> = _buttonTag


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

    @RequiresApi(Build.VERSION_CODES.O)
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

                    val invoiceByYearList = salseInvoiceByYearUseCase(
                        token = token.toString(),
                        startDate = startDate.value.orEmpty(),
                        endDate = endDate.value.orEmpty()
                    )

                    _salesInvoiceByYearList.postValue(invoiceByYearList)
                    withContext(Dispatchers.Main) {
                        getButtonTagWiseDate()
                    }
                    delay(1000L)
                    withContext(Dispatchers.IO) {
                        val stateWiseSalesDetails = fetchStateWiseSalesInvoiceDetailsUseCase(
                            token = token.toString(),
                            startDate = _startDateChart.value.orEmpty(),
                            endDate = _endDateChart.value.orEmpty()
                        )
                        _stateWiseSalesInvoiceDetailList.postValue(stateWiseSalesDetails)
                        Log.d("SALES", stateWiseSalesDetails.toString())
                    }
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

    fun updateButtonTag(buttonTag: String) {
        _buttonTag.value = buttonTag
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


    @RequiresApi(Build.VERSION_CODES.O)
    fun getButtonTagWiseDate() {
        DateTimeFormatter.ofPattern("yyyy-MM-dd")
        when (buttonTag.value) {
            "1M" -> {
                val endDate = LocalDate.now()
                val startDate = endDate.withDayOfMonth(1)

                _endDateChart.value = endDate.toString()
                _startDateChart.value = startDate.toString()

                viewModelScope.launch(Dispatchers.IO) {
                    getTokenUseCase().collect { token ->
                        val stateWiseSalesDetails = fetchStateWiseSalesInvoiceDetailsUseCase(
                            token = token.toString(),
                            startDate = _startDateChart.value.orEmpty(),
                            endDate = _endDateChart.value.orEmpty()
                        )
                        _stateWiseSalesInvoiceDetailList.postValue(stateWiseSalesDetails)

                    }

                }

            }

            "3M" -> {
                val endDate = LocalDate.now()
                val startDate = endDate.minusMonths(2).withDayOfMonth(1)

                _endDateChart.value = endDate.toString()
                _startDateChart.value = startDate.toString()

                viewModelScope.launch(Dispatchers.IO) {
                    getTokenUseCase().collect { token ->
                        val stateWiseSalesDetails = fetchStateWiseSalesInvoiceDetailsUseCase(
                            token = token.toString(),
                            startDate = _startDateChart.value.orEmpty(),
                            endDate = _endDateChart.value.orEmpty()
                        )
                        _stateWiseSalesInvoiceDetailList.postValue(stateWiseSalesDetails)

                    }

                }

            }

            "6M" -> {
                val endDate = LocalDate.now()
                val startDate = endDate.minusMonths(5).withDayOfMonth(1)

                _endDateChart.value = endDate.toString()
                _startDateChart.value = startDate.toString()
                viewModelScope.launch(Dispatchers.IO) {
                    getTokenUseCase().collect { token ->
                        val stateWiseSalesDetails = fetchStateWiseSalesInvoiceDetailsUseCase(
                            token = token.toString(),
                            startDate = _startDateChart.value.orEmpty(),
                            endDate = _endDateChart.value.orEmpty()
                        )
                        _stateWiseSalesInvoiceDetailList.postValue(stateWiseSalesDetails)

                    }

                }
            }

            "1Y" -> {
                _endDateChart.value = _endDate.value.toString()
                _startDateChart.value = _startDate.value.toString()
                viewModelScope.launch(Dispatchers.IO) {
                    getTokenUseCase().collect { token ->
                        val stateWiseSalesDetails = fetchStateWiseSalesInvoiceDetailsUseCase(
                            token = token.toString(),
                            startDate = _startDateChart.value.orEmpty(),
                            endDate = _endDateChart.value.orEmpty()
                        )
                        _stateWiseSalesInvoiceDetailList.postValue(stateWiseSalesDetails)

                    }

                }
            }

        }


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