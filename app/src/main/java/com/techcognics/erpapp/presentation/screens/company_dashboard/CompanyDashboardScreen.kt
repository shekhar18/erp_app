package com.techcognics.erpapp.presentation.screens.company_dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techcognics.erpapp.presentation.component.AutoResponsiveCardGrid
import com.techcognics.erpapp.presentation.component.CompanyDashboardTitle
import com.techcognics.erpapp.presentation.component.DateRangePicker
import com.techcognics.erpapp.presentation.component.charts.CardChart

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CompanyDashboardScreen(
    modifier: Modifier = Modifier, /*homeNavController: NavHostController*/
    paddingValue: PaddingValues
) {
    val viewModel: CompanyDashboardScreenViewModel = hiltViewModel()
    LazyColumn(
        modifier = modifier
            .padding(paddingValue)
            .fillMaxSize()
    ) {
        item {
            DateRangePicker(onDateRangeSelected = { start, end ->
                viewModel.updateStartDate(startDate = start.toString())
                viewModel.updateEndString(endDate = end.toString())
            }, viewModel = viewModel)
            Spacer(modifier = modifier.height(10.dp))
        }

        item {
            CompanyDashboardTitle()
        }
        item {
            Box(
                modifier = Modifier
                    .height(400.dp)
                    .padding(horizontal = 8.dp)
            ) {
                ResponsiveDashboard()
            }
        }


    }


}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun ShowCompanyDashboardScreen() {
    //CompanyDashboardScreen()
}

@Composable
fun ResponsiveDashboard() {
    val mockDataList = listOf(
        listOf(30f, 45f, 40f, 50f, 35f, 42f, 20f),
        listOf(30f, 45f, 40f, 50f, 35f, 42f, 20f),
        listOf(15f, 25f, 35f),
        listOf(30f, 45f, 40f, 50f, 35f, 42f, 20f)
    )

    val cards = mockDataList.mapIndexed { index, data ->
        @Composable {
            CardChart(
                title = "Total Income",
                amount = "83,320.50",
                changePercentage = 18.2f,
                isPositive = true,
                barData = data
            )
        }
    }

    AutoResponsiveCardGrid(cards = cards)
}