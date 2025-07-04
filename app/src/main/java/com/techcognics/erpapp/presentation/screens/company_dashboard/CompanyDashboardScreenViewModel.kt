package com.techcognics.erpapp.presentation.screens.company_dashboard

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcognics.erpapp.data.BarDataCard
import com.techcognics.erpapp.data.CompanyLineCard
import com.techcognics.erpapp.data.CompanyStatusCard
import com.techcognics.erpapp.data.SwitchableMultipleLineAndBar
import com.techcognics.erpapp.data.SwitchableSingleLineAndBarCard
import com.techcognics.erpapp.data.company_dashboard_data.AllTotalAmountResponse
import com.techcognics.erpapp.data.company_dashboard_data.AmountsByMonthResponse
import com.techcognics.erpapp.domain.usecase.GetTokenUseCase
import com.techcognics.erpapp.domain.usecase.company_dashboard_usecase.AllSalesInvoiceByMonthUseCase
import com.techcognics.erpapp.domain.usecase.company_dashboard_usecase.AllTotalAmountsUseCase
import com.techcognics.erpapp.domain.usecase.company_dashboard_usecase.AmountsByMonthUseCase
import com.techcognics.erpapp.domain.usecase.company_dashboard_usecase.TotalIncomeAmountUseCase
import com.techcognics.erpapp.presentation.base.Result
import com.techcognics.erpapp.util.getRandomColor
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ehsannarmani.compose_charts.models.Bars
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CompanyDashboardScreenViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val allSalesInvoiceByMonthUseCase: AllSalesInvoiceByMonthUseCase,
    private val totalIncomeAmountUseCase: TotalIncomeAmountUseCase,
    private val allTotalAmountsUseCase: AllTotalAmountsUseCase,
    private val amountsByMonthUseCase: AmountsByMonthUseCase
) : ViewModel() {

    private val _startDate = MutableLiveData<String>()
    val startDate: LiveData<String> = _startDate

    private val _endDate = MutableLiveData<String>()
    val endDate: LiveData<String> = _endDate

    private val _docNo = MutableLiveData("D09")
    val docNo: LiveData<String> = _docNo

    private val _docNoTwo = MutableLiveData("D06")
    val docNoTwo: LiveData<String> = _docNoTwo

    private val _companyStatusCardList = MutableLiveData<List<CompanyStatusCard>>(emptyList())
    val companyStatusCardList: LiveData<List<CompanyStatusCard>> = _companyStatusCardList

    private val _companyLineChartCardList = MutableLiveData<List<CompanyLineCard>>(emptyList())
    val companyLineChartCardList: LiveData<List<CompanyLineCard>> = _companyLineChartCardList

    private val _switchableCardList = MutableLiveData<List<SwitchableSingleLineAndBarCard>>(emptyList())
    val switchableCardList: LiveData<List<SwitchableSingleLineAndBarCard>> = _switchableCardList

    private val _switchableMultiLineAndBarCardList = MutableLiveData<List<SwitchableMultipleLineAndBar>>(emptyList())
    val switchableMultiLineAndBarCardList: LiveData<List<SwitchableMultipleLineAndBar>> = _switchableMultiLineAndBarCardList
    private val _amountsByMonthList = MutableLiveData<List<AmountsByMonthResponse>>(emptyList())
    val amountsByMonthList: LiveData<List<AmountsByMonthResponse>> = _amountsByMonthList

    private val _companyDashboardState = MutableLiveData<Result<Unit>>(Result.Idle)
    val companyDashboardState: LiveData<Result<Unit>> = _companyDashboardState

    val headers = listOf(
        "Month", "Cash at EOM", "Account Payable",
        "Account Receivable", "Quick Ratio", "Current Ratio"
    )

    /*init {
        companyDashboardApiCalls()
    }*/

    fun updateStartDate(startDate: String) {
        _startDate.value = startDate
    }

    fun updateEndDate(endDate: String) {
        _endDate.value = endDate
    }

    fun resetScreen() {
        _companyDashboardState.value = Result.Idle
    }

    fun companyDashboardApiCalls() {
        viewModelScope.launch {
            _companyDashboardState.value = Result.Loading
            try {
                getTokenUseCase().collect { token ->
                    val tokenStr = token.toString()

                    val totalIncomeAndExpenses = totalIncomeAmountUseCase(
                        token = tokenStr, startDate = _startDate.value.orEmpty(), endDate = _endDate.value.orEmpty()
                    ) ?: emptyList()

                    val salesD09 = allSalesInvoiceByMonthUseCase(
                        token = tokenStr, docNo = _docNo.value.orEmpty(),
                        startDate = _startDate.value.orEmpty(), endDate = _endDate.value.orEmpty()
                    ) ?: emptyList()

                    val salesD06 = allSalesInvoiceByMonthUseCase(
                        token = tokenStr, docNo = _docNoTwo.value.orEmpty(),
                        startDate = _startDate.value.orEmpty(), endDate = _endDate.value.orEmpty()
                    ) ?: emptyList()

                    val totalAmounts = allTotalAmountsUseCase(
                        token = tokenStr, docNo = _docNo.value.orEmpty(),
                        startDate = _startDate.value.orEmpty(), endDate = _endDate.value.orEmpty()
                    ) ?: emptyList()

                    val monthlyAmounts = amountsByMonthUseCase(
                        token = tokenStr, docNo = _docNo.value.orEmpty(),
                        startDate = _startDate.value.orEmpty(), endDate = _endDate.value.orEmpty()
                    ) ?: emptyList()

                    _amountsByMonthList.value = monthlyAmounts

                    val statusCards = listOf(
                        CompanyStatusCard("Total Income", totalIncomeAndExpenses.map { it.totalIncome.toDouble() },
                            totalIncomeAndExpenses.sumOf { it.totalIncome.toDouble() }, 0.0, Color(0xFFDE0A71)),

                        CompanyStatusCard("Total Expenses", totalIncomeAndExpenses.map { it.totalExpense.toDouble() },
                            totalIncomeAndExpenses.sumOf { it.totalExpense.toDouble() }, 0.0, Color(0xFF13C6C0)),

                        CompanyStatusCard("ACCOUNTS RECEIVABLE", salesD09.map { it.totalAmount.toDouble() },
                            getAccountAmount(totalAmounts, "Account Receivable"), 0.0, Color(0xFFFFEB3B)),

                        CompanyStatusCard("ACCOUNTS PAYABLE", salesD06.map { it.totalAmount.toDouble() },
                            salesD06.sumOf { it.totalAmount.toDouble() }, 0.0, Color(0xFFF44336))
                    )

                    _companyStatusCardList.value = statusCards

                    val lineCards = listOf(
                        CompanyLineCard(
                            label = getAccountAmount(totalAmounts, "Account Receivable").toString(),
                            dataList = salesD09.map { it.totalAmount.toDouble() },
                            firstGradientFillColor = Color(0xFF2942BB),
                            secondGradientFillColor = Color.Transparent,
                            lineThickness = 2.5.dp
                        ),
                        CompanyLineCard(
                            label = "Sum of Cash EOM ${salesD09.sumOf { it.totalAmount.toDouble() }}",
                            dataList = salesD09.map { it.totalAmount.toDouble() },
                            firstGradientFillColor = Color(0xFF673AB7),
                            secondGradientFillColor = Color.Transparent,
                            lineThickness = 0.5.dp
                        )
                    )
                    _companyLineChartCardList.value = lineCards

                    val switchableCards = listOf(
                        SwitchableSingleLineAndBarCard(
                            listLineData = totalIncomeAndExpenses.map { it.totalIncome.toDouble() },
                            listLineLabel = totalIncomeAndExpenses.map { it.month },
                            barListDate = totalIncomeAndExpenses.map {
                                BarDataCard(
                                    label = it.month, data = listOf(
                                        Bars.Data(
                                            value = it.totalIncome.toDouble(),
                                            label = it.totalIncome,
                                            color = SolidColor(getRandomColor())
                                        )
                                    )
                                )
                            },
                            color =getRandomColor()
                        )
                    )
                    _switchableCardList.value = switchableCards

                    val incomeAndExpensesList = listOf(
                        SwitchableMultipleLineAndBar(
                            listOfIncome = totalIncomeAndExpenses.map { it.totalIncome.toDouble() },
                            listOfExpenses = totalIncomeAndExpenses.map { it.totalExpense.toDouble() },
                            listOfMonths = totalIncomeAndExpenses.map { it.month },
                            listOfBarData = totalIncomeAndExpenses.map {
                                BarDataCard(
                                    label = it.month, data = listOf(
                                        Bars.Data(
                                            value = it.totalIncome.toDouble(),
                                            label = it.totalIncome,
                                            color = SolidColor(getRandomColor())
                                        ),
                                        Bars.Data(
                                            value = it.totalExpense.toDouble(),
                                            label = it.totalExpense,
                                            color = SolidColor(getRandomColor())
                                        )
                                    )
                                )
                            },

                        )
                    )

                    _switchableMultiLineAndBarCardList.value = incomeAndExpensesList


                    _companyDashboardState.value = Result.Success(Unit)
                }
            } catch (e: Exception) {
                _companyDashboardState.value = Result.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    private fun getAccountAmount(list: List<AllTotalAmountResponse>, accountName: String): Double {
        return list.find { it.accountName == accountName }?.totalAmount?.toDoubleOrNull() ?: 0.0
    }
}