package com.techcognics.erpapp.presentation.screens.company_dashboard

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcognics.erpapp.data.CompanyStatusCard
import com.techcognics.erpapp.domain.usecase.GetTokenUseCase
import com.techcognics.erpapp.domain.usecase.company_dashboard_usecase.AllSalesInvoiceByMonthUseCase
import com.techcognics.erpapp.domain.usecase.company_dashboard_usecase.TotalIncomeAmountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CompanyDashboardScreenViewModel @Inject constructor(
    val getTokenUseCase: GetTokenUseCase,
    val allSalesInvoiceByMonthUseCase: AllSalesInvoiceByMonthUseCase,
    val totalIncomeAmountUseCase: TotalIncomeAmountUseCase
) : ViewModel() {

    private val _startDate: MutableLiveData<String> = MutableLiveData<String>()
    val startDate: LiveData<String> = _startDate

    private val _endDate: MutableLiveData<String> = MutableLiveData<String>()
    val endDate: LiveData<String> = _endDate

    private val _docNo: MutableLiveData<String> = MutableLiveData<String>("D09")
    val docNo: LiveData<String> = _docNo

    private val _cardList: MutableLiveData<List<CompanyStatusCard>> =
        MutableLiveData<List<CompanyStatusCard>>(emptyList<CompanyStatusCard>())
    val cardList: LiveData<List<CompanyStatusCard>> = _cardList


    init {
        //implement api Call
        viewModelScope.launch {
            getTokenUseCase.invoke().collect { token ->
                val totalIncomeAndExpenses = totalIncomeAmountUseCase.invoke(
                    token = token.toString(),
                    startDate = startDate.value.toString(),
                    endDate = endDate.value.toString()
                )

                val companyStatusCardOne = CompanyStatusCard(
                    title = "Total Income",
                    data = totalIncomeAndExpenses.map { it.totalIncome.toDouble() },
                    totalAmount = totalIncomeAndExpenses.sumOf { it.totalIncome.toDouble() },
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

                _cardList.value = _cardList.value + listOf(
                    companyStatusCardOne,
                    companyStatusCardTwo
                )


            }

        }
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