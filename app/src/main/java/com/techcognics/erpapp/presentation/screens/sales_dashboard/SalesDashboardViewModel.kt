package com.techcognics.erpapp.presentation.screens.sales_dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.SolidColor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcognics.erpapp.data.BarDataCard
import com.techcognics.erpapp.data.RowBarData
import com.techcognics.erpapp.data.sales_dashboard_data.AllTotalAmountsOfSalesResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FetchItemGroupDetailsPercentageResponse
import com.techcognics.erpapp.data.sales_dashboard_data.FiscalYearOfSalesResponse
import com.techcognics.erpapp.data.sales_dashboard_data.SalesInvoiceByYearResponse
import com.techcognics.erpapp.data.sales_dashboard_data.StateWiseSalesInvoiceDetailsResponse
import com.techcognics.erpapp.domain.usecase.GetTokenUseCase
import com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase.AllTotalAmountsOfSalesUseCase
import com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase.FetchItemGroupDetailsPercentageUseCase
import com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase.FetchSalesByTopCustomerUseCase
import com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase.FetchStateWiseSalesInvoiceDetailsUseCase
import com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase.GetFetchAllSalesByGrowthUseCase
import com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase.GetFetchAllSalesInvoiceByQuarterlyUseCase
import com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase.GetSalesYearUseCase
import com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase.GetSalseInvoiceByYearUseCase
import com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase.GetTopSalesDetailsUseCase
import com.techcognics.erpapp.presentation.base.Result
import com.techcognics.erpapp.util.getRandomColor
import com.techcognics.erpapp.util.toRowBarData
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ehsannarmani.compose_charts.models.Bars
import kotlinx.coroutines.Dispatchers
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
    private val fetchStateWiseSalesInvoiceDetailsUseCase: FetchStateWiseSalesInvoiceDetailsUseCase,
    private val fetchItemGroupDetailsPercentageUseCase: FetchItemGroupDetailsPercentageUseCase,
    private val fetchSalesByTopCustomerUseCase: FetchSalesByTopCustomerUseCase,
    private val getTopSalesDetailsUseCase: GetTopSalesDetailsUseCase,
    private val getFetchAllSalesInvoiceByQuarterlyUseCase: GetFetchAllSalesInvoiceByQuarterlyUseCase,
    private val getFetchAllSalesByGrowthUseCase: GetFetchAllSalesByGrowthUseCase
) : ViewModel() {

    // Dashboard state
    private val _salesDashboardState = MutableLiveData<Result<Unit>>(Result.Idle)
    val salesDashboardState: LiveData<Result<Unit>> = _salesDashboardState

    // Date ranges
    private val _startDate = MutableLiveData<String>()
    val startDate: LiveData<String> = _startDate

    private val _endDate = MutableLiveData<String>()
    val endDate: LiveData<String> = _endDate

    private val _startDateChart = MutableLiveData<String>()
    val startDateChart: LiveData<String> = _startDateChart

    private val _endDateChart = MutableLiveData<String>()
    val endDateChart: LiveData<String> = _endDateChart

    private val _startDateGrowthChart = MutableLiveData<String>()
    val startDateGrowthChart: LiveData<String> = _startDateGrowthChart

    private val _endDateGrowthChart = MutableLiveData<String>()
    val endDateGrowthChart: LiveData<String> = _endDateGrowthChart

    // Lists from API
    private val _yearList = MutableLiveData<List<FiscalYearOfSalesResponse>>()
    val yearList: LiveData<List<FiscalYearOfSalesResponse>> = _yearList

    private val _salesTilesData = MutableLiveData<List<AllTotalAmountsOfSalesResponse>>()
    val salesTilesData: LiveData<List<AllTotalAmountsOfSalesResponse>> = _salesTilesData

    private val _salesInvoiceByYearList = MutableLiveData<List<SalesInvoiceByYearResponse>>()
    val salesInvoiceByYearList: LiveData<List<SalesInvoiceByYearResponse>> = _salesInvoiceByYearList

    private val _stateWiseSalesInvoiceDetailList =
        MutableLiveData<List<StateWiseSalesInvoiceDetailsResponse>>()
    val stateWiseSalesInvoiceDetailList: LiveData<List<StateWiseSalesInvoiceDetailsResponse>> =
        _stateWiseSalesInvoiceDetailList

    private val _groupByDetails = MutableLiveData<List<FetchItemGroupDetailsPercentageResponse>>()
    val groupByDetails: LiveData<List<FetchItemGroupDetailsPercentageResponse>> = _groupByDetails

    // Bar chart data
    private val _topCustomer = MutableLiveData<List<RowBarData>>(emptyList())
    val topCustomer: LiveData<List<RowBarData>> = _topCustomer

    private val _topSales = MutableLiveData<List<RowBarData>>(emptyList())
    val topSales: LiveData<List<RowBarData>> = _topSales

    private val _salesOfQuarter = MutableLiveData<List<RowBarData>>(emptyList())
    val salesOfQuarter: LiveData<List<RowBarData>> = _salesOfQuarter

    private val _salesGrowth = MutableLiveData<List<RowBarData>>(emptyList())
    val salesGrowth: LiveData<List<RowBarData>> = _salesGrowth

    // Button tag states
    private val _buttonTag = MutableLiveData("1Y")
    val buttonTag: LiveData<String> = _buttonTag

    private val _buttonTagGrowth = MutableLiveData("1Y")
    val buttonTagGrowth: LiveData<String> = _buttonTagGrowth

    private val _buttonTagByGroupOrCustomer = MutableLiveData("Item Group Wise sales")
    val buttonTagByGroupOrCustomer: LiveData<String> = _buttonTagByGroupOrCustomer
    fun getFiscalYear() {
        viewModelScope.launch {
            _salesDashboardState.value = Result.Loading
            getTokenUseCase().collect { token ->
                val result = runCatching {
                    withContext(Dispatchers.IO) {
                        getSalesYearUseCase(token.orEmpty())
                    }
                }
                result.onSuccess { yearList ->
                    _yearList.value = yearList
                    _salesDashboardState.value = Result.Idle
                }.onFailure {
                    _salesDashboardState.value = Result.Error(it.message ?: "Unknown error")
                }
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun getFetchSalesDashboardData() {
        viewModelScope.launch {
            _salesDashboardState.value = Result.Loading

            getTokenUseCase().collect { token ->
                val safeToken = token.orEmpty()

                val start = startDate.value.orEmpty()
                val end = endDate.value.orEmpty()
                val chartStart = _startDateChart.value.orEmpty()
                val chartEnd = _endDateChart.value.orEmpty()

                // Fetch in IO context
                val salesData = withContext(Dispatchers.IO) {
                    val tiles = allTotalAmountsOfSaleUseCase(safeToken, start, end)
                    val invoiceByYear = salseInvoiceByYearUseCase(safeToken, start, end)

                    val stateWise =
                        fetchStateWiseSalesInvoiceDetailsUseCase(safeToken, chartStart, chartEnd)
                    val groupItems = fetchItemGroupDetailsPercentageUseCase(safeToken, start, end)

                    val topCustomerRaw = fetchSalesByTopCustomerUseCase(safeToken, start, end)
                    val topSalesRaw = getTopSalesDetailsUseCase(safeToken, start, end)
                    val quarterlySales =
                        getFetchAllSalesInvoiceByQuarterlyUseCase(safeToken, start, end)
                    val growthRaw = getFetchAllSalesByGrowthUseCase(safeToken, start, end)

                    DashboardData(
                        tiles = mergeSalesData(defaultList(), tiles),
                        invoiceByYear = invoiceByYear,
                        stateWise = stateWise,
                        groupItems = groupItems,
                        topCustomer = topCustomerRaw.toRowBarData { it.customerName to it.totalAmount.toDouble() },
                        topSales = topSalesRaw.toRowBarData {
                            (it.itemDescription ?: "") to (it.totalGrossAmount ?: 0.0)
                        },
                        quarterly = quarterlySales.toRowBarData {
                            (it.month ?: "") to (it.totalAmount ?: 0.0)
                        },
                        growth = growthRaw.toRowBarData {
                            (it.month ?: "") to (it.percentage?.toDouble() ?: 0.0)
                        })
                }

                // Assign values on Main thread
                _salesTilesData.value = salesData.tiles
                _salesInvoiceByYearList.value = salesData.invoiceByYear
                _stateWiseSalesInvoiceDetailList.value = salesData.stateWise
                _groupByDetails.value = salesData.groupItems
                _topCustomer.value = salesData.topCustomer
                _topSales.value = salesData.topSales
                _salesOfQuarter.value = salesData.quarterly
                _salesGrowth.value = salesData.growth

                // Call other data functions
                getButtonTagWiseDate()
                getSalseGrowthByMonthsAndYear()

                _salesDashboardState.value = Result.Idle
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

    fun updateButtonTagGrowth(buttonTag: String) {
        _buttonTagGrowth.value = buttonTag
    }

    fun updateButtonTagByGroupOrCustomer(buttonTag: String) {
        _buttonTagByGroupOrCustomer.value = buttonTag
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
        val (start, end) = getChartDateRange(buttonTag.value.orEmpty())
        _startDateChart.value = start.toString()
        _endDateChart.value = end.toString()

        viewModelScope.launch(Dispatchers.IO) {
            getTokenUseCase().collect { token ->
                val stateWiseSalesDetails = fetchStateWiseSalesInvoiceDetailsUseCase(
                    token = token.toString(),
                    startDate = start.toString(),
                    endDate = end.toString()
                )
                _stateWiseSalesInvoiceDetailList.postValue(stateWiseSalesDetails)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getChartDateRange(tag: String): Pair<LocalDate, LocalDate> {
        val today = LocalDate.now()
        return when (tag) {
            "1M" -> {
                val start = today.withDayOfMonth(1)
                start to today
            }
            "3M" -> {
                val start = today.minusMonths(2).withDayOfMonth(1)
                start to today
            }
            "6M" -> {
                val start = today.minusMonths(5).withDayOfMonth(1)
                start to today
            }
            "1Y" -> {
                val start = _startDate.value?.let { LocalDate.parse(it) } ?: today.minusYears(1)
                val end = _endDate.value?.let { LocalDate.parse(it) } ?: today
                start to end
            }
            else -> {
                val start = today.withDayOfMonth(1)
                start to today
            }
        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun getSalseGrowthByMonthsAndYear() {
        val (start, end) = getGrowthChartDateRange(buttonTagGrowth.value.orEmpty())
        _startDateGrowthChart.value = start.toString()
        _endDateGrowthChart.value = end.toString()

        viewModelScope.launch(Dispatchers.IO) {
            getTokenUseCase().collect { token ->
                val growthData = getFetchAllSalesByGrowthUseCase(
                    token = token.orEmpty(),
                    startDate = start.toString(),
                    endDate = end.toString()
                )
                if (growthData.isNotEmpty()) {
                    _salesGrowth.postValue(growthData.toRowBarData {
                        (it.month ?: "") to (it.percentage?.toDouble() ?: 0.0)
                    })
                }
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getGrowthChartDateRange(tag: String): Pair<LocalDate, LocalDate> {
        val today = LocalDate.now()
        return when (tag) {
            "1M" -> today.withDayOfMonth(1) to today
            "3M" -> today.minusMonths(2).withDayOfMonth(1) to today
            "6M" -> today.minusMonths(5).withDayOfMonth(1) to today
            "1Y" -> {
                val start = _startDate.value?.let { LocalDate.parse(it) } ?: today.minusYears(1)
                val end = _endDate.value?.let { LocalDate.parse(it) } ?: today
                start to end
            }
            else -> today.withDayOfMonth(1) to today
        }
    }


    fun mergeSalesData(
        defaultList: List<AllTotalAmountsOfSalesResponse>,
        serverList: List<AllTotalAmountsOfSalesResponse>
    ): List<AllTotalAmountsOfSalesResponse> {
        val serverMap = serverList.associateBy { it.accountName }
        return defaultList.map { defaultItem ->
            val serverItem = serverMap[defaultItem.accountName]
            if (serverItem != null) {
                defaultItem.copy(
                    totalAmount = serverItem.totalAmount,
                    totalCount = serverItem.totalCount
                )
            } else {
                defaultItem
            }
        }
    }

}


data class DashboardData(
    val tiles: List<AllTotalAmountsOfSalesResponse>,
    val invoiceByYear: List<SalesInvoiceByYearResponse>,
    val stateWise: List<StateWiseSalesInvoiceDetailsResponse>,
    val groupItems: List<FetchItemGroupDetailsPercentageResponse>,
    val topCustomer: List<RowBarData>,
    val topSales: List<RowBarData>,
    val quarterly: List<RowBarData>,
    val growth: List<RowBarData>,
)
