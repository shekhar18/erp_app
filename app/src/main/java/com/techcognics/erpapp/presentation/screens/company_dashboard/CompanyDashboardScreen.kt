package com.techcognics.erpapp.presentation.screens.company_dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techcognics.erpapp.presentation.base.Result
import com.techcognics.erpapp.presentation.component.AutoResponsiveCardGrid
import com.techcognics.erpapp.presentation.component.DashboardTitle
import com.techcognics.erpapp.presentation.component.CopyrightFooter
import com.techcognics.erpapp.presentation.component.DateRangePicker
import com.techcognics.erpapp.presentation.component.DynamicTable
import com.techcognics.erpapp.presentation.component.ErrorDialog
import com.techcognics.erpapp.presentation.component.Loader
import com.techcognics.erpapp.presentation.component.charts.CardBarChart
import com.techcognics.erpapp.presentation.component.charts.CardLineChart
import com.techcognics.erpapp.presentation.component.charts.SwitchableMultipleLineAndBarCard
import com.techcognics.erpapp.presentation.component.charts.SwitchableSingleLineAndBar
import com.techcognics.erpapp.util.extractTableData
import com.techcognics.erpapp.util.getRandomColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CompanyDashboardScreen(
    modifier: Modifier = Modifier, paddingValue: PaddingValues
) {
    val viewModel: CompanyDashboardScreenViewModel = hiltViewModel()
    val state by viewModel.companyDashboardState.observeAsState(Result.Idle)

    LaunchedEffect(true) {
        viewModel.companyDashboardApiCalls()
    }

    when (state) {
        is Result.Loading -> Loader()

        is Result.Error -> {
            val message = (state as Result.Error).message
            ErrorDialog(
                onClick = {
                    viewModel.companyDashboardApiCalls()
                }, message = message
            )
        }

        is Result.Idle, is Result.Success -> {
            LazyColumn(
                modifier = modifier
                    .padding(paddingValue)
                    .fillMaxSize()
            ) {
                item {
                    DateRangePicker(
                        onDateRangeSelected = { start, end ->
                            viewModel.updateStartDate(start.toString())
                            viewModel.updateEndDate(end.toString())
                            viewModel.companyDashboardApiCalls()
                        }, viewModel = viewModel
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }

                item { DashboardTitle(label = "COMPANY DASHBOARD") }

                item {
                    CompanyStatusCardSection(viewModel)
                }

                item {
                    CompanyLineChartSection(viewModel)
                }

                item {
                    SwitchableCardSection(viewModel)
                }
                item {
                    SwitchableMultiLineAndBarCardSection(viewModel)
                }

                item {
                    AmountsTableSection(viewModel)
                }

                item { CopyrightFooter() }
            }
        }
    }
}

@Composable
fun CompanyStatusCardSection(viewModel: CompanyDashboardScreenViewModel) {
    val cards = viewModel.companyStatusCardList.observeAsState().value.orEmpty()

    Box(
        modifier = Modifier.height(380.dp)
            .padding(horizontal = 8.dp)
    ) {
        val cardContent = cards.map {
            @Composable {
                CardBarChart(
                    title = it.title,
                    amount = it.totalAmount.toString(),
                    changePercentage = it.percentage,
                    isPositive = true,
                    barData = it.data,
                    color = it.color
                )
            }
        }
        AutoResponsiveCardGrid(columnsSize = 2, cards = cardContent)
    }
}

@Composable
fun CompanyLineChartSection(viewModel: CompanyDashboardScreenViewModel) {
    val cards = viewModel.companyLineChartCardList.observeAsState().value.orEmpty()

    Box(
        modifier = Modifier
            .height(200.dp)
            .padding(horizontal = 8.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Sum of Account Receivable",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.W900
                )
            }

            val cardContent = cards.map {
                @Composable {
                    CardLineChart(
                        label = it.label,
                        modifier = Modifier,
                        dataList = it.dataList,
                        firstGradientFillColor = it.firstGradientFillColor,
                        secondGradientFillColor = it.secondGradientFillColor,
                        lineThickness = it.lineThickness
                    )
                }
            }
            AutoResponsiveCardGrid(columnsSize = 2, cards = cardContent)
        }
    }
}

@Composable
fun SwitchableCardSection(viewModel: CompanyDashboardScreenViewModel) {
    val switchableCards = viewModel.switchableCardList.observeAsState().value.orEmpty()

    Box(
        modifier = Modifier
            .height(300.dp)
            .padding(horizontal = 8.dp, vertical = 10.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(22.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Sum of Sales by month",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.W900
                )
            }

            val cards: List<@Composable () -> Unit> = switchableCards.map {
                @Composable {
                    var isLineChart by remember { mutableStateOf(true) }
                    SwitchableSingleLineAndBar(
                        modifier = Modifier,
                        showLine = isLineChart,
                        lineData = it.listLineData,
                        lineLabels = it.listLineLabel,
                        listOfBarData = it.barListDate,
                        color = it.color,
                    )
                }
            }

            if (cards.isNotEmpty()) {
                Column {
                    cards[0]()
                }
            }


        }
    }
}


@Composable
fun SwitchableMultiLineAndBarCardSection(viewModel: CompanyDashboardScreenViewModel) {
    val cardList = viewModel.switchableMultiLineAndBarCardList.observeAsState().value.orEmpty()
    Box(
        modifier = Modifier
            .height(300.dp)
            .padding(horizontal = 8.dp, vertical = 10.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(22.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Sum of income and sum of expenses by month",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.W900
                )
            }
            val cards: List<@Composable () -> Unit> = cardList.map {
                @Composable {
                    var isLineChart by remember { mutableStateOf(true) }
                    SwitchableMultipleLineAndBarCard(
                        listOfIncome = it.listOfIncome,
                        listOfExpenses = it.listOfExpenses,
                        listOfMonth = it.listOfMonths,
                        listOfBarData = it.listOfBarData,
                        incomeColor = getRandomColor(),
                        expensesColor = getRandomColor(),
                        showLine = isLineChart
                    )

                }
            }
            if (cards.isNotEmpty()) {
                Column {
                    cards[0]()
                }
            }


        }
    }

}

@Composable
fun AmountsTableSection(viewModel: CompanyDashboardScreenViewModel) {
    val tableData = extractTableData(viewModel.amountsByMonthList.observeAsState().value.orEmpty())

    Box(
        modifier = Modifier
            .height(300.dp)
            .padding(horizontal = 8.dp, vertical = 10.dp)
    ) {
        DynamicTable(
            headers = viewModel.headers, data = tableData, modifier = Modifier.padding(16.dp)
        )
    }
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun ShowCompanyDashboardScreen() {
    //CompanyDashboardScreen()
}

