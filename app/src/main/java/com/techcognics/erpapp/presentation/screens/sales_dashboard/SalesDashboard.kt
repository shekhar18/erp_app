package com.techcognics.erpapp.presentation.screens.sales_dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techcognics.erpapp.presentation.base.Result
import com.techcognics.erpapp.presentation.component.AutoResponsiveCardGrid
import com.techcognics.erpapp.presentation.component.DashboardTitle
import com.techcognics.erpapp.presentation.component.ErrorDialog
import com.techcognics.erpapp.presentation.component.Loader
import com.techcognics.erpapp.presentation.component.SalesComparisonCard
import com.techcognics.erpapp.presentation.component.charts.CardLineChart
import com.techcognics.erpapp.presentation.component.charts.Pie
import com.techcognics.erpapp.presentation.component.dropdownmenu.YearDropdown
import com.techcognics.erpapp.util.getRandomColor
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SalesDashboard(modifier: Modifier = Modifier, paddingValue: PaddingValues) {

    val viewModel: SalesDashboardViewModel = hiltViewModel()
    val state by viewModel.salesDashboardState.observeAsState()
    var selectedYear by rememberSaveable { mutableStateOf<String?>(LocalDate.now().year.toString()) }

    LaunchedEffect(true) {
        viewModel.getFiscalYear()
        getStartAndEndDate(selectedYear = LocalDate.now().year.toString(), viewModel)
        viewModel.getFetchSalesDashboardData()
    }



    when (state) {
        is Result.Loading -> Loader()
        is Result.Error -> {
            val message = (state as Result.Error).message
            ErrorDialog(
                onClick = {
                    // viewModel.companyDashboardApiCalls()
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
                    YearDropdown(
                        modifier = modifier,
                        years = viewModel.yearList.observeAsState().value,
                        currentYear = selectedYear,
                        onYearSelected = { year ->
                            selectedYear = year
                            getStartAndEndDate(year, viewModel)
                            viewModel.getFetchSalesDashboardData()
                        })
                    Spacer(modifier = Modifier.height(10.dp))
                }
                item { DashboardTitle(label = "SALES DASHBOARD") }
                item {
                    SalesStatusCardSection(viewModel)
                }
                item {
                    SalesComparisonCardSection(viewModel)
                    Spacer(modifier = Modifier.height(10.dp))
                }
                item {
                    SalesByStateCardSection(viewModel)
                }
            }
        }

        else -> {}
    }


    /*  val salesState = listOf(
          StatCard(
              "Revenue", "₹50", "Cr", Color(0xFFFFCDD2), Color(0xFFFFEBEE), R.drawable.rad_chart
          ), StatCard(
              "Expected Revenue",
              "₹13",
              "Lakhs",
              Color(0xFFC8E6C9),
              Color(0xFFE8F5E9),
              R.drawable.green_chart
          ), StatCard(
              "Sales Orders",
              "₹12",
              "Lakhs",
              Color(0xFFD1C4E9),
              Color(0xFFEDE7F6),
              R.drawable.purple_chart
          ), StatCard(
              "All Values",
              "₹12",
              "Lakhs",
              Color(0xFFFFE0B2),
              Color(0xFFFFF3E0),
              R.drawable.yellow_chart
          )
      )*/

    /*   Column(
           modifier = modifier
               .fillMaxSize()
               .padding(paddingValue)
       ) {
           Row(
               modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
           ) {
               Text(
                   "Techcognics India Pvt LTD Sales Dashboard",
                   style = MaterialTheme.typography.bodyLarge,
                   color = MaterialTheme.colorScheme.primary,
                   modifier = Modifier.padding(8.dp),
               )
           }
           LazyVerticalGrid(
               columns = GridCells.Fixed(2),
               contentPadding = PaddingValues(8.dp),
               verticalArrangement = Arrangement.spacedBy(12.dp),
               horizontalArrangement = Arrangement.spacedBy(12.dp),
           ) {
               items(salesState.size) { index ->
                   StatCardItem(
                       card = salesState[index],
                       modifier = Modifier
                           .fillMaxWidth()
                           .aspectRatio(1.6f) // adjust ratio to fit nicely
                   )
               }
           }
           SalesComparisonCard()
           Spacer(modifier = modifier.height(10.dp))
           Card(
               modifier = modifier
                   .height(400.dp)
                   .width(350.dp),
               shape = RoundedCornerShape(4.dp),
               elevation = CardDefaults.cardElevation(
                   defaultElevation = 8.dp,
               ),
               colors = CardDefaults.cardColors(
                   containerColor = MaterialTheme.colorScheme.background
               )
           ) {
               Pie()
           }
       }*/
}


@RequiresApi(Build.VERSION_CODES.O)
fun getStartAndEndDate(selectedYear: String, viewModel: SalesDashboardViewModel) {

    val endDate = "$selectedYear-03-31"
    viewModel.updateEndDate(endDate.toString())  // e.g. 2024-07-08


    val start = "${selectedYear.toInt() - 1}-04-01"
    viewModel.updateStartDate(start.toString())     // e.g. 2023-07-08}
}


@Composable
fun SalesStatusCardSection(viewModel: SalesDashboardViewModel) {
    val cards = viewModel.salesTilesData.observeAsState().value.orEmpty()

    Box(
        modifier = Modifier
            .height(350.dp)
            .padding(horizontal = 8.dp)
    ) {

        val filterData = cards.filter { it.displayName.isNotEmpty() }.sortedBy { it.order }

        val cardContent = filterData.map {

            @Composable {
                CardLineChart(
                    label = "${it.displayName} \n ₹ ${it.totalAmount}",
                    lineCurve = false,
                    showBorder = true,
                    modifier = Modifier,
                    dataList = listOf(0.0, it.totalAmount),
                    firstGradientFillColor = getRandomColor(),
                    secondGradientFillColor = Color.Transparent,
                    lineThickness = 1.dp,
                )
            }
        }
        AutoResponsiveCardGrid(columnsSize = 2, cards = cardContent)
    }
}

@Composable
fun SalesComparisonCardSection(viewModel: SalesDashboardViewModel) {
    val invoiceList = viewModel.salesInvoiceByYearList.observeAsState().value.orEmpty()
    Box(
        modifier = Modifier
            .height(50.dp)
            .padding(horizontal = 8.dp)
    ) {
        if (invoiceList.isNotEmpty()) {
            SalesComparisonCard(
                modifier = Modifier,
                salseCount = invoiceList.last().totalAmount,
                persentage = invoiceList.last().percentage,
                previousYear = invoiceList.first().years.toString()
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
            Pie(
                modifier = Modifier, buttonState = buttonState, onclick = { buttonTag ->
                    viewModel.updateButtonTag(buttonTag)
                    viewModel.getButtonTagWiseDate()

                }, data = viewModel.stateWiseSalesInvoiceDetailList.observeAsState().value ?: emptyList()
            )
        }
    }

}


@Preview
@Composable
private fun ShowSalesDashboard() {
    // SalesDashboard()
}