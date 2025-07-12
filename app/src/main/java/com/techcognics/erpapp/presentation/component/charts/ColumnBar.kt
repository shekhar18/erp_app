package com.techcognics.erpapp.presentation.component.charts

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import ir.ehsannarmani.compose_charts.ColumnChart
import ir.ehsannarmani.compose_charts.models.BarProperties
import ir.ehsannarmani.compose_charts.models.Bars
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.IndicatorPosition
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.LabelProperties

@Composable
fun ColumnBar(modifier: Modifier = Modifier, list: List<BarDataCard>) {
    ColumnChart(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp)
            .height(200.dp), data = remember {
            list.map {
                Bars(
                    label = it.label, values = it.data
                )
            }.toList()
        }, indicatorProperties = HorizontalIndicatorProperties(
            enabled = true,
            position = IndicatorPosition.Horizontal.Start,
            textStyle = TextStyle(
                fontSize = 12.sp, color = MaterialTheme.colorScheme.onBackground
            ),
        ), barProperties = BarProperties(
            thickness = 15.dp, spacing = 4.dp, cornerRadius = Bars.Data.Radius.Circular(0.dp)
        ), animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow
        ), labelProperties = LabelProperties(
            enabled = true, builder = { modifier, label, shouldRotate, index ->
                Text(
                    modifier = modifier,
                    text = label,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }, padding = 10.dp, rotation = LabelProperties.Rotation(
                mode = LabelProperties.Rotation.Mode.Force, degree = -40f
            )
        ), labelHelperProperties = LabelHelperProperties(
            enabled = false,
        )
    )
}