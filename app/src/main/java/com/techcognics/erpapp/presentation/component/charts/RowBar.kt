package com.techcognics.erpapp.presentation.component.charts

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.techcognics.erpapp.data.BarDataCard
import com.techcognics.erpapp.presentation.screens.sales_dashboard.SalesDashboardViewModel
import ir.ehsannarmani.compose_charts.RowChart
import ir.ehsannarmani.compose_charts.models.BarProperties
import ir.ehsannarmani.compose_charts.models.Bars

@Composable
fun RowBar(modifier: Modifier = Modifier, list: List<BarDataCard>) {






    Box(
        modifier = Modifier.fillMaxSize()

    ) {
        RowChart(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp),
            data = remember {
                list.map {
                    Bars(
                        label = it.label, values = it.data
                    )
                }.toList()
            },
            barProperties = BarProperties(
                thickness = 15.dp, spacing = 4.dp, cornerRadius = Bars.Data.Radius.Circular(6.dp)
            ),
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow
            ),
        )
    }

}