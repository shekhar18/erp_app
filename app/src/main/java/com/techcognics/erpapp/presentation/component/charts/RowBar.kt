package com.techcognics.erpapp.presentation.component.charts

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techcognics.erpapp.data.BarDataCard
import ir.ehsannarmani.compose_charts.RowChart
import ir.ehsannarmani.compose_charts.models.BarProperties
import ir.ehsannarmani.compose_charts.models.Bars
import ir.ehsannarmani.compose_charts.models.IndicatorPosition
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.LabelProperties
import ir.ehsannarmani.compose_charts.models.VerticalIndicatorProperties

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
            indicatorProperties = VerticalIndicatorProperties(
                enabled = true,
                position = IndicatorPosition.Vertical.Bottom,
                textStyle = TextStyle(
                    fontSize = 12.sp, color = MaterialTheme.colorScheme.onBackground
                ),
            ),labelHelperProperties = LabelHelperProperties(
                enabled = false,
            ),
            barProperties = BarProperties(
                thickness = 15.dp, spacing = 4.dp, cornerRadius = Bars.Data.Radius.Circular(6.dp)
            ),
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow
            ),
            labelProperties = LabelProperties(
                enabled = true,
                builder = { modifier, label, shouldRotate, index ->
                    Text(
                        modifier = modifier,
                        text = label,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                padding = 16.dp,
            ),
        )
    }

}