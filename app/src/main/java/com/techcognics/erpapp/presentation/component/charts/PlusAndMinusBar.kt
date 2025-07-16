package com.techcognics.erpapp.presentation.component.charts

import androidx.compose.foundation.layout.fillMaxSize
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
fun PlusAndMinusBar(modifier: Modifier = Modifier, list: List<BarDataCard>) {
    ColumnChart(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .height(220.dp),
        data = remember {
            list.map {
                Bars(
                    label = it.label, values = it.data.toList()
                )
            }.toList()
        },
        maxValue = 100.0,
        minValue = -100.0,
        indicatorProperties = HorizontalIndicatorProperties(
            enabled = true,
            position = IndicatorPosition.Horizontal.Start,
            textStyle = TextStyle(
                fontSize = 12.sp, color = MaterialTheme.colorScheme.onBackground
            ),
        ),
        barProperties = BarProperties(
            thickness = 15.dp, spacing = 0.dp, cornerRadius = Bars.Data.Radius.Circular(0.dp)
        ),
        labelProperties = LabelProperties(
            enabled = true, builder = { modifier, label, shouldRotate, index ->
                Text(
                    modifier = modifier,
                    text = label,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },rotation = LabelProperties.Rotation(
                mode = LabelProperties.Rotation.Mode.Force, degree = -45f
            )
        ),
        labelHelperProperties = LabelHelperProperties(
            enabled = false,
        )
    )
}