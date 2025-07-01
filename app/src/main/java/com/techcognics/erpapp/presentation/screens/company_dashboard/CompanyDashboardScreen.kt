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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.techcognics.erpapp.presentation.component.CompanyDashboardTitle
import com.techcognics.erpapp.presentation.component.CopyrightFooter
import com.techcognics.erpapp.presentation.component.DateRangePicker
import com.techcognics.erpapp.presentation.component.DynamicTable
import com.techcognics.erpapp.presentation.component.ErrorDialog
import com.techcognics.erpapp.presentation.component.Loader
import com.techcognics.erpapp.presentation.component.charts.CardBarChart
import com.techcognics.erpapp.presentation.component.charts.CardLineChart
import com.techcognics.erpapp.presentation.component.charts.SwitchableSingleLineAndBar
import com.techcognics.erpapp.util.extractTableData


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CompanyDashboardScreen(
    modifier: Modifier = Modifier, /*homeNavController: NavHostController*/
    paddingValue: PaddingValues
) {
    val viewModel: CompanyDashboardScreenViewModel = hiltViewModel()
    val componyStateCards by viewModel.companyStatusCardList.observeAsState()
    val companyLineChartCards by viewModel.companyLineChartCardList.observeAsState()
    val switchableCard by viewModel.switchableCardList.observeAsState()
    val companyDashboardResultState by viewModel.companyDashboardState.observeAsState()

   when(companyDashboardResultState){
       is Result.Idle -> LazyColumn(
               modifier = modifier
                   .padding(paddingValue)
                   .fillMaxSize()
           ) {
               item {
                   DateRangePicker(onDateRangeSelected = { start, end ->
                       viewModel.updateStartDate(startDate = start.toString())
                       viewModel.updateEndString(endDate = end.toString())
                       viewModel.companyDashboardApiCalls()
                   }, viewModel = viewModel)
                   Spacer(modifier = modifier.height(10.dp))
               }
               item {
                   CompanyDashboardTitle()
               }
               item {
                   Box(
                       modifier = Modifier
                           .height(350.dp)
                           .padding(horizontal = 8.dp)
                   ) {

                       val cards: List<@Composable () -> Unit> = componyStateCards?.map {
                           {
                               CardBarChart(
                                   title = it.title,
                                   amount = it.totalAmount.toString(),
                                   changePercentage = it.percentage,
                                   isPositive = true,
                                   barData = it.data,
                                   color = it.color
                               )
                           }
                       } ?: emptyList()
                       AutoResponsiveCardGrid(columnsSize = 2, cards = cards)
                   }
               }
               item {
                   Box(
                       modifier = Modifier
                           .height(200.dp)
                           .padding(horizontal = 8.dp)
                   ) {
                       val cards: List<@Composable () -> Unit> = companyLineChartCards?.map {
                           @Composable {
                               CardLineChart(
                                   label = it.label.toString(),
                                   modifier = Modifier,
                                   dataList = it.dataList,
                                   firstGradientFillColor = it.firstGradientFillColor,
                                   secondGradientFillColor = it.secondGradientFillColor,
                                   lineThickness = it.lineThickness,
                               )
                           }
                       } ?: emptyList()
                       Column {
                           Row(
                               modifier = modifier
                                   .fillMaxWidth()
                                   .height(32.dp),
                               horizontalArrangement = Arrangement.Absolute.Center
                           ) {
                               Text(
                                   text = "Sum of Account Receivable",
                                   style = MaterialTheme.typography.bodySmall,
                                   fontWeight = FontWeight.W900
                               )
                           }
                           AutoResponsiveCardGrid(columnsSize = 2, cards = cards)
                       }

                   }
               }
               item {
                   Box(
                       modifier = Modifier
                           .height(300.dp)
                           .padding(horizontal = 8.dp, vertical = 10.dp)
                   ) {
                       val cards = switchableCard?.map {
                           @Composable {
                               var isLineChart by remember { mutableStateOf(true) }
                               SwitchableSingleLineAndBar(
                                   modifier = modifier,
                                   showLine = isLineChart,
                                   lineData = it.listLineData,
                                   lineLabels = it.listLineLabel,
                                   listOfBarData = it.barListDate,
                               )
                           }
                       } ?: emptyList()
                       Column {
                           Row(
                               modifier = modifier
                                   .fillMaxWidth()
                                   .height(22.dp),
                               horizontalArrangement = Arrangement.Absolute.Center
                           ) {
                               Text(
                                   text = "Sum of Sales by month",
                                   style = MaterialTheme.typography.bodySmall,
                                   fontWeight = FontWeight.W900
                               )
                           }
                           AutoResponsiveCardGrid(
                               cards = cards
                           )
                       }
                   }

               }
               item {
                   Box(
                       modifier = Modifier
                           .height(300.dp)
                           .padding(horizontal = 8.dp, vertical = 10.dp)
                   ) {
                       DynamicTable(
                           headers = viewModel.headers,
                           data = extractTableData(viewModel.amountsByMonthList.observeAsState().value?.toList()),
                           modifier = Modifier.padding(16.dp)
                       )
                   }

               }
               item {
                   CopyrightFooter()

               }
           }
       is Result.Loading -> Loader()
       is Result.Error -> ErrorDialog { viewModel }
       else -> {}
   }




}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun ShowCompanyDashboardScreen() {
    //CompanyDashboardScreen()
}

