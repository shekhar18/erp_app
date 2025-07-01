package com.techcognics.erpapp.presentation.screens.company_dashboard

import android.annotation.SuppressLint
import android.util.Log
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
import com.techcognics.erpapp.data.SwitchableSingleLineAndBarCard
import com.techcognics.erpapp.data.company_dashboard_data.AllTotalAmountResponse
import com.techcognics.erpapp.data.company_dashboard_data.AmountsByMonthResponse
import com.techcognics.erpapp.domain.usecase.GetTokenUseCase
import com.techcognics.erpapp.domain.usecase.company_dashboard_usecase.AllSalesInvoiceByMonthUseCase
import com.techcognics.erpapp.domain.usecase.company_dashboard_usecase.AllTotalAmountsUseCase
import com.techcognics.erpapp.domain.usecase.company_dashboard_usecase.AmountsByMonthUseCase
import com.techcognics.erpapp.domain.usecase.company_dashboard_usecase.TotalIncomeAmountUseCase
import com.techcognics.erpapp.presentation.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ehsannarmani.compose_charts.models.Bars
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CompanyDashboardScreenViewModel @Inject constructor(
    val getTokenUseCase: GetTokenUseCase,
    val allSalesInvoiceByMonthUseCase: AllSalesInvoiceByMonthUseCase,
    val totalIncomeAmountUseCase: TotalIncomeAmountUseCase,
    val allTotalAmountsUseCase: AllTotalAmountsUseCase,
    val amountsByMonthUseCase: AmountsByMonthUseCase
) : ViewModel() {

    private val _startDate: MutableLiveData<String> = MutableLiveData<String>()
    val startDate: LiveData<String> = _startDate

    private val _endDate: MutableLiveData<String> = MutableLiveData<String>()
    val endDate: LiveData<String> = _endDate

    private val _docNo: MutableLiveData<String> = MutableLiveData<String>("D09")
    val docNo: LiveData<String> = _docNo

    private val _docNoTwo: MutableLiveData<String> = MutableLiveData<String>("D06")
    val docNoTwo: LiveData<String> = _docNoTwo

    private val _companyStatusCardList: MutableLiveData<List<CompanyStatusCard>> =
        MutableLiveData<List<CompanyStatusCard>>(emptyList<CompanyStatusCard>())
    val companyStatusCardList: LiveData<List<CompanyStatusCard>> = _companyStatusCardList

    private val _companyLineChartCardList: MutableLiveData<List<CompanyLineCard>> =
        MutableLiveData<List<CompanyLineCard>>(emptyList<CompanyLineCard>())
    val companyLineChartCardList: LiveData<List<CompanyLineCard>> = _companyLineChartCardList

    private val _switchableCardList: MutableLiveData<List<SwitchableSingleLineAndBarCard>> =
        MutableLiveData<List<SwitchableSingleLineAndBarCard>>(emptyList<SwitchableSingleLineAndBarCard>())

    val switchableCardList: LiveData<List<SwitchableSingleLineAndBarCard>> = _switchableCardList

    val headers = listOf(
        "Month",
        "Cash at EOM",
        "Account Payable",
        "Account Receivable",
        "Quick Ratio",
        "Current Ratio"
    )

    private val _amountsByMonthList: MutableLiveData<List<AmountsByMonthResponse>> =
        MutableLiveData<List<AmountsByMonthResponse>>(emptyList<AmountsByMonthResponse>())

    val amountsByMonthList: LiveData<List<AmountsByMonthResponse>> = _amountsByMonthList

    private val _companyDashboardState =
        MutableLiveData<Result<Result.Idle>>(
            Result.Idle
        )
    val companyDashboardState: LiveData<Result<Result.Idle>> = _companyDashboardState


    init {
        companyDashboardApiCalls()
    }

    fun resetScreen() {
        _companyDashboardState.value = Result.Idle
    }

    //implement api Call
    @SuppressLint("DefaultLocale")
    fun companyDashboardApiCalls() {
        viewModelScope.launch {

            getTokenUseCase.invoke().collect { token ->
                _companyDashboardState.value = Result.Loading
                val totalIncomeAndExpenses = totalIncomeAmountUseCase.invoke(
                    token = token.toString(),
                    startDate = startDate.value.toString() ?: "",
                    endDate = endDate.value.toString() ?: ""
                ) ?: emptyList()

                val allSalesInvoiceByMonthD09Response = allSalesInvoiceByMonthUseCase.invoke(
                    token = token.toString(),
                    docNo = _docNo.value.toString(),
                    startDate = _startDate.value.toString(),
                    endDate = _endDate.value.toString()
                ) ?: emptyList()

                val allSalesInvoiceByMonthD06Response = allSalesInvoiceByMonthUseCase.invoke(
                    token = token.toString(),
                    docNo = _docNoTwo.value.toString(),
                    startDate = _startDate.value.toString(),
                    endDate = _endDate.value.toString()
                ) ?: emptyList()
                val allTotalAmountsResponse = allTotalAmountsUseCase.invoke(
                    token = token.toString(),
                    docNo = _docNo.value.toString(),
                    startDate = _startDate.value.toString(),
                    endDate = _endDate.value.toString()
                ) ?: emptyList()

                _amountsByMonthList.value =
                    (amountsByMonthList.value + amountsByMonthUseCase.invoke(
                        token = token.toString(),
                        docNo = _docNo.value.toString(),
                        startDate = _startDate.value.toString(),
                        endDate = _endDate.value.toString()
                    )) ?: emptyList()

                _companyDashboardState.value = Result.Idle

                val companyStatusCardOne = CompanyStatusCard(
                    title = "Total Income",
                    data = totalIncomeAndExpenses.map { it.totalIncome.toDouble() },
                    totalAmount = String.format(
                        "%.2f", totalIncomeAndExpenses.sumOf { it.totalIncome.toDouble() })
                        .toDouble(),
                    percentage = 0.0,
                    color = Color(0xFFDE0A71)
                )

                val companyStatusCardTwo = CompanyStatusCard(
                    title = "Total Expenses",
                    data = totalIncomeAndExpenses.map { it.totalExpense.toDouble() },
                    totalAmount = totalIncomeAndExpenses.sumOf { it.totalExpense.toDouble() },
                    percentage = 0.0,
                    color = Color(0xFF13C6C0)
                )

                val companyStatusCardThird = CompanyStatusCard(
                    title = "ACCOUNTS RECEIVABLE",
                    data = allSalesInvoiceByMonthD09Response.map { it.totalAmount.toDouble() },
                    totalAmount = getAmountOfAccountReceivable(allTotalAmountsResponse).toDouble(),
                    percentage = 0.0,
                    color = Color(0xFFFFEB3B)
                )

                val companyStatusCardForth = CompanyStatusCard(
                    title = "ACCOUNTS PAYABLE",
                    data = allSalesInvoiceByMonthD06Response.map { it.totalAmount.toDouble() },
                    totalAmount = allSalesInvoiceByMonthD06Response.sumOf { it.totalAmount.toDouble() },
                    percentage = 0.0,
                    color = Color(0xFFF44336)
                )
                _companyStatusCardList.value = emptyList<CompanyStatusCard>()
                _companyStatusCardList.value = _companyStatusCardList.value + listOf(
                    companyStatusCardOne,
                    companyStatusCardTwo,
                    companyStatusCardThird,
                    companyStatusCardForth
                )


                val companyLineCardOne = CompanyLineCard(
                    label = String.format(
                        "%.2f", getAmountOfAccountReceivable(allTotalAmountsResponse).toDouble()
                    ),
                    dataList = allSalesInvoiceByMonthD09Response.map { it.totalAmount.toDouble() },
                    firstGradientFillColor = Color(0xFF2942BB),
                    secondGradientFillColor = Color.Transparent,
                    lineThickness = 2.5.dp
                )
                val companyLineCardTwo = CompanyLineCard(
                    label = "Sum of Cash EOM  " + String.format(
                        "%.2f", getAmountOfAccountReceivable(allTotalAmountsResponse).toDouble()
                    ),
                    dataList = allSalesInvoiceByMonthD09Response.map { it.totalAmount.toDouble() },
                    firstGradientFillColor = Color(0xFFBB3F29),
                    secondGradientFillColor = Color.Transparent,
                    lineThickness = .5.dp
                )


                _companyLineChartCardList.value = emptyList<CompanyLineCard>()
                _companyLineChartCardList.value =
                    _companyLineChartCardList.value + listOf<CompanyLineCard>(
                        companyLineCardOne, companyLineCardTwo,

                        )

                val switchableCardOne = SwitchableSingleLineAndBarCard(
                    listLineData = totalIncomeAndExpenses.map { it.totalIncome.toDouble() },
                    listLineLabel = totalIncomeAndExpenses.map { it.month },
                    barListDate = totalIncomeAndExpenses.map {
                        BarDataCard(
                            label = it.month, data = listOf(
                                Bars.Data(
                                    value = it.totalIncome.toDouble(),
                                    label = it.totalIncome,
                                    color = SolidColor(Color(0xFF2942BB))
                                )
                            )
                        )
                    })

                _switchableCardList.value = emptyList<SwitchableSingleLineAndBarCard>()
                _switchableCardList.value =
                    _switchableCardList.value + listOf<SwitchableSingleLineAndBarCard>(
                        switchableCardOne
                    )


            }

        }
    }


    fun getAmountOfAccountReceivable(allTotalAmountsResponse: List<AllTotalAmountResponse>): String {
        val dataObject = allTotalAmountsResponse.find { it.accountName == "Account Receivable" }

        return dataObject?.totalAmount.toString()
    }

    fun updateStartDate(startDate: String) {
        Log.d("CDVMS", startDate)
        _startDate.value = startDate
    }

    fun updateEndString(endDate: String) {
        Log.d("CDVMS", endDate)
        _endDate.value = endDate
    }


}