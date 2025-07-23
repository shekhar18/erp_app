package com.techcognics.erpapp.presentation.screens.sales_dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techcognics.erpapp.presentation.base.Result
import com.techcognics.erpapp.presentation.component.AutoResponsiveCardGrid
import com.techcognics.erpapp.presentation.component.ScreenTitle
import com.techcognics.erpapp.presentation.component.ErrorDialog
import com.techcognics.erpapp.presentation.component.Loader
import com.techcognics.erpapp.presentation.component.SalesComparisonCard
import com.techcognics.erpapp.presentation.component.button.BoarderButton
import com.techcognics.erpapp.presentation.component.charts.CardLineChart
import com.techcognics.erpapp.presentation.component.charts.ColumnBar
import com.techcognics.erpapp.presentation.component.charts.Pie
import com.techcognics.erpapp.presentation.component.charts.PlusAndMinusBar
import com.techcognics.erpapp.presentation.component.charts.RowBar
import com.techcognics.erpapp.presentation.component.dropdownmenu.YearDropdown
import com.techcognics.erpapp.util.getRandomColor
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SalesDashboard(modifier: Modifier = Modifier, paddingValue: PaddingValues) {
    val viewModel: SalesDashboardViewModel = hiltViewModel()
    val state by viewModel.salesDashboardState.observeAsState()
    var selectedYear by rememberSaveable { mutableStateOf(LocalDate.now().year.toString()) }

    LaunchedEffect(true) {
        viewModel.getFiscalYear()
        viewModel.getButtonTagWiseDate()
        viewModel.getSalseGrowthByMonthsAndYear()
        getStartAndEndDate(selectedYear, viewModel)
        viewModel.getFetchSalesDashboardData()
    }

    when (state) {
        is Result.Loading -> Loader()
        is Result.Error -> ErrorDialog(onClick = {}, message = (state as Result.Error).message)
        is Result.Idle, is Result.Success -> {
            LazyColumn(
                modifier = modifier
                    .padding(paddingValue)
                    .fillMaxSize()
            ) {
                item {
                    YearDropdown(
                        years = viewModel.yearList.observeAsState().value,
                        currentYear = selectedYear,
                        onYearSelected = {
                            selectedYear = it
                            getStartAndEndDate(it, viewModel)
                            viewModel.getFetchSalesDashboardData()
                        }
                    )
                }
                item {
                    ScreenTitle(
                        titleFirst = "SALES DASHBOARD",
                        titleSecond = "Overview analysis"
                    )
                }
                item { SalesStatusCard(viewModel) }
                item { SalesComparisonCardSection(viewModel) }
                item { SalesByStateCardSection(viewModel) }
                item { TopSalesCardSection(viewModel) }
                item { SalesByGroupAndTopCustomerCardSection(viewModel) }
                item { SalesOfQuarterCardSection(viewModel) }
                item { SalesOfGrowthCardSection(viewModel) }

            }
        }

        null -> TODO()
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun getStartAndEndDate(selectedYear: String, viewModel: SalesDashboardViewModel) {

    val endDate = "$selectedYear-03-31"
    viewModel.updateEndDate(endDate.toString())  // e.g. 2024-07-08


    val start = "${selectedYear.toInt() - 1}-04-01"
    viewModel.updateStartDate(start.toString())     // e.g. 2023-07-08}
}



@Composable
fun SalesStatusCard(viewModel: SalesDashboardViewModel) {
    val cards by viewModel.salesTilesData.observeAsState(emptyList())

    val cardContent = remember(cards) {
        cards
            .filter { it.displayName.isNotEmpty() }
            .sortedBy { it.order }
            .map { item ->
                @Composable {
                    CardLineChart(
                        label = "${item.displayName} \n ₹ ${item.totalAmount}",
                        lineCurve = false,
                        showBorder = true,
                        modifier = Modifier,
                        dataList = listOf(0.0, item.totalAmount),
                        firstGradientFillColor = getRandomColor(),
                        secondGradientFillColor = Color.Transparent,
                        lineThickness = 1.dp,
                    )
                }
            }
    }

    Box(
        modifier = Modifier
            .height(350.dp)
            .padding(horizontal = 8.dp)
    ) {
        AutoResponsiveCardGrid(columnsSize = 2, cards = cardContent)
    }
}

@Composable
fun SalesComparisonCardSection(viewModel: SalesDashboardViewModel) {
    val invoiceList by viewModel.salesInvoiceByYearList.observeAsState(emptyList())

    if (invoiceList.isNotEmpty()) {
        val lastInvoice = invoiceList.last()
        val firstYear = invoiceList.first().years.toString()

        Box(
            modifier = Modifier
                .height(50.dp)
                .padding(horizontal = 8.dp)
        ) {
            SalesComparisonCard(
                modifier = Modifier,
                salseCount = lastInvoice.totalAmount,
                persentage = lastInvoice.percentage,
                previousYear = firstYear
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SalesByStateCardSection(viewModel: SalesDashboardViewModel) {
    val buttonState by viewModel.buttonTag.observeAsState()

    Column(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = if (isSystemInDarkTheme()) 10.dp else 8.dp,
                    ambientColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.5f) else Color.Black.copy(
                        alpha = 0.5f
                    ),
                    spotColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.5f) else Color.Black.copy(
                        alpha = 0.5f
                    )
                ), shape = RoundedCornerShape(4.dp), elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp,
            ), colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, top = 10.dp, end = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    text = "Salse(Amt) By State",
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W600,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(25.dp))
                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    BoarderButton(
                        modifier = Modifier,
                        onclick = {
                            viewModel.updateButtonTag("1M")
                            viewModel.getButtonTagWiseDate()

                        },
                        label = "1M",
                        buttonState = buttonState,
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    BoarderButton(
                        modifier = Modifier,
                        onclick = {
                            viewModel.updateButtonTag("3M")
                            viewModel.getButtonTagWiseDate()

                        },
                        label = "3M",
                        buttonState = buttonState,
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    BoarderButton(
                        modifier = Modifier,
                        onclick = {
                            viewModel.updateButtonTag("6M")
                            viewModel.getButtonTagWiseDate()

                        },
                        label = "6M",
                        buttonState = buttonState,
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    BoarderButton(
                        modifier = Modifier, buttonState = buttonState, onclick = {
                        viewModel.updateButtonTag("1Y")
                        viewModel.getButtonTagWiseDate()

                    }, label = "1Y")

                }
            }


            Pie(
                data = viewModel.stateWiseSalesInvoiceDetailList.observeAsState().value
            )
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SalesByGroupAndTopCustomerCardSection(viewModel: SalesDashboardViewModel) {
    val buttonState by viewModel.buttonTagByGroupOrCustomer.observeAsState()
    val customer = viewModel.topCustomer.observeAsState().value

    Column(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = if (isSystemInDarkTheme()) 10.dp else 8.dp,
                    ambientColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.5f) else Color.Black.copy(
                        alpha = 0.5f
                    ),
                    spotColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.5f) else Color.Black.copy(
                        alpha = 0.5f
                    )
                ), shape = RoundedCornerShape(4.dp), elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp,
            ), colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    BoarderButton(label = "Item Group Wise sales", onclick = {
                        viewModel.updateButtonTagByGroupOrCustomer("Item Group Wise sales")
                    }, buttonState = buttonState)
                    Spacer(modifier = Modifier.width(10.dp))
                    BoarderButton(label = "Sales by Top 5 Customer", onclick = {
                        viewModel.updateButtonTagByGroupOrCustomer("Sales by Top 5 Customer")

                    }, buttonState = buttonState)
                }
                if (buttonState != "Sales by Top 5 Customer") {
                    Pie(
                        modifier = Modifier,
                        data = viewModel.groupByDetails.observeAsState().value ?: emptyList()
                    )
                } else {


                    Column(modifier = Modifier.padding(5.dp)) {
                        if (customer?.isEmpty() == true) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 25.dp),
                                horizontalArrangement = Arrangement.Absolute.Center,

                                ) {
                                Text(
                                    "No Data Found",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            }
                        } else {
                            customer?.forEach { customerData ->
                                RowBar(modifier = Modifier, list = customerData.barListDate)
                            }
                        }

                    }

                }

            }

        }
    }

}

@Composable
fun TopSalesCardSection(viewModel: SalesDashboardViewModel) {
    val topSales = viewModel.topSales.observeAsState()
    Box(
        modifier = Modifier
            .height(300.dp)
            .padding(15.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .shadow(
                    elevation = if (isSystemInDarkTheme()) 10.dp else 8.dp,
                    ambientColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.5f) else Color.Black.copy(
                        alpha = 0.5f
                    ),
                    spotColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.5f) else Color.Black.copy(
                        alpha = 0.5f
                    )
                ), shape = RoundedCornerShape(4.dp), elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp,
            ), colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            if (topSales.value.orEmpty().isNotEmpty()) {
                Column {
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "  Salse Top 5 Items",
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    topSales.value.orEmpty().forEach { customerData ->
                        ColumnBar(modifier = Modifier, customerData.barListDate)
                    }
                }


            } else {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .weight(0.2f)
                    ) {
                        Text(
                            text = "  Salse Top 5 Items",
                            style = MaterialTheme.typography.titleSmall,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W600,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        "No Data Found",
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }
}

@Composable
fun SalesOfQuarterCardSection(viewModel: SalesDashboardViewModel) {
    val salesOfQuarter = viewModel.salesOfQuarter.observeAsState()
    Box(
        modifier = Modifier
            .height(300.dp)
            .padding(15.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .shadow(
                    elevation = if (isSystemInDarkTheme()) 10.dp else 8.dp,
                    ambientColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.5f) else Color.Black.copy(
                        alpha = 0.5f
                    ),
                    spotColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.5f) else Color.Black.copy(
                        alpha = 0.5f
                    )
                ), shape = RoundedCornerShape(4.dp), elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp,
            ), colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            if (salesOfQuarter.value.orEmpty().isNotEmpty()) {
                Column {
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "  Salse of Quarterly",
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    salesOfQuarter.value.orEmpty().forEach { customerData ->
                        ColumnBar(modifier = Modifier, customerData.barListDate)
                    }
                }


            } else {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .weight(0.2f)
                    ) {
                        Text(
                            text = "  Salse of Quarterly",
                            style = MaterialTheme.typography.titleSmall,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W600,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        "No Data Found",
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SalesOfGrowthCardSection(viewModel: SalesDashboardViewModel) {
    val salseGrowth = viewModel.salesGrowth.observeAsState()
    val forGrowthButton = viewModel.buttonTagGrowth.observeAsState()
    Box(
        modifier = Modifier
            .height(280.dp)
            .padding(15.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .shadow(
                    elevation = if (isSystemInDarkTheme()) 10.dp else 8.dp,
                    ambientColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.5f) else Color.Black.copy(
                        alpha = 0.5f
                    ),
                    spotColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.5f) else Color.Black.copy(
                        alpha = 0.5f
                    )
                ), shape = RoundedCornerShape(4.dp), elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp,
            ), colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            if (salseGrowth.value.orEmpty().isNotEmpty()) {

                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp, top = 10.dp, end = 5.dp, bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Text(
                            text = "Salse Growth %",
                            style = MaterialTheme.typography.titleSmall,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W600,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(25.dp))
                        Row(
                            horizontalArrangement = Arrangement.End
                        ) {
                            BoarderButton(
                                modifier = Modifier,
                                onclick = {
                                    viewModel.updateButtonTagGrowth("1M")
                                    viewModel.getSalseGrowthByMonthsAndYear()
                                },
                                label = "1M",
                                buttonState = forGrowthButton.value,
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            BoarderButton(
                                modifier = Modifier,
                                onclick = {
                                    viewModel.updateButtonTagGrowth("3M")
                                    viewModel.getSalseGrowthByMonthsAndYear()

                                },
                                label = "3M",
                                buttonState = forGrowthButton.value,
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            BoarderButton(
                                modifier = Modifier,
                                onclick = {
                                    viewModel.updateButtonTagGrowth("6M")
                                    viewModel.getSalseGrowthByMonthsAndYear()
                                },
                                label = "6M",
                                buttonState = forGrowthButton.value,
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            BoarderButton(
                                modifier = Modifier,
                                buttonState = forGrowthButton.value,
                                onclick = {
                                    viewModel.updateButtonTagGrowth("1Y")
                                    viewModel.getSalseGrowthByMonthsAndYear()
                                },
                                label = "1Y"
                            )

                        }

                    }
                    salseGrowth.value.orEmpty().map { customerData ->
                        PlusAndMinusBar(modifier = Modifier, customerData.barListDate)
                    }
                }


            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp, top = 10.dp, end = 5.dp, bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Text(
                            text = "Salse Growth %",
                            style = MaterialTheme.typography.titleSmall,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W600,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(25.dp))
                        Row(
                            horizontalArrangement = Arrangement.End
                        ) {
                            BoarderButton(
                                modifier = Modifier,
                                onclick = {


                                },
                                label = "1M",
                                buttonState = forGrowthButton.value.toString(),
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            BoarderButton(
                                modifier = Modifier,
                                onclick = {


                                },
                                label = "3M",
                                buttonState = forGrowthButton.value.toString(),
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            BoarderButton(
                                modifier = Modifier,
                                onclick = {

                                },
                                label = "6M",
                                buttonState = forGrowthButton.value.toString(),
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            BoarderButton(
                                modifier = Modifier,
                                buttonState = forGrowthButton.value.toString(),
                                onclick = {

                                },
                                label = "1Y"
                            )

                        }

                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        "No Data Found",
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }
}

@Preview
@Composable
private fun ShowSalesDashboard() {
    // SalesDashboard()
}