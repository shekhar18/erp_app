package com.techcognics.erpapp.presentation.component.charts


import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techcognics.erpapp.data.BarDataCard
import com.techcognics.erpapp.presentation.component.CustomSwitch
import ir.ehsannarmani.compose_charts.ColumnChart
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.BarProperties
import ir.ehsannarmani.compose_charts.models.Bars
import ir.ehsannarmani.compose_charts.models.DividerProperties
import ir.ehsannarmani.compose_charts.models.DotProperties
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.GridProperties
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.IndicatorPosition
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.LabelProperties
import ir.ehsannarmani.compose_charts.models.Line
import ir.ehsannarmani.compose_charts.models.LineProperties

@Composable
fun SwitchableSingleLineAndBar(
    modifier: Modifier = Modifier,
    showLine: Boolean = true,
    lineData: List<Double>,
    lineLabels: List<String>,
    listOfBarData: List<BarDataCard>
) {

    var showLineState by rememberSaveable { mutableStateOf(showLine) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = modifier
            .padding(4.dp)
            .height(300.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background).shadow(
                elevation = if (isSystemInDarkTheme()) 10.dp else 8.dp,
                ambientColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.5f) else Color.Black.copy(alpha = 0.5f),
                spotColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.5f) else Color.Black.copy(alpha = 0.5f)
            ),
    ) {
        Column(modifier = modifier.padding(end = 5.dp, bottom = 5.dp, start = 5.dp).fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .padding(top = 10.dp, end = 5.dp),
                horizontalArrangement = Arrangement.End
            ) {
                CustomSwitch(isLineChart = showLineState) {
                    showLineState = it
                }
            }
            if (showLineState) {
                //show line chart
                LineChart(
                    curvedEdges = false,
                    indicatorProperties = HorizontalIndicatorProperties(
                        enabled = true,
                        position = IndicatorPosition.Horizontal.Start,
                        textStyle = MaterialTheme.typography.labelSmall,
                    ),
                    dividerProperties = DividerProperties(
                        enabled = true,
                        xAxisProperties = LineProperties(enabled = true),
                        yAxisProperties = LineProperties(enabled = true)
                    ),
                    labelProperties = LabelProperties(
                        padding = 2.dp,
                        enabled = true,
                        labels = lineLabels,
                        builder = { modifier, label, shouldRotate, index ->
                            Text(
                                modifier = modifier,
                                text = label,
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        },
                        rotation = LabelProperties.Rotation(
                            mode = LabelProperties.Rotation.Mode.Force,
                            degree = if (lineData.size > 5) {
                                -45f
                            } else {
                                0f
                            }
                        )

                    ),
                    labelHelperProperties = LabelHelperProperties(enabled = false),
                    gridProperties = GridProperties(
                        enabled = true,
                        xAxisProperties = GridProperties.AxisProperties(enabled = true),
                        yAxisProperties = GridProperties.AxisProperties(
                            enabled = true, color = SolidColor(Color.Transparent)
                        )
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 5.dp),
                    data = remember {
                        listOf(
                            Line(
                                label = "",
                                values = lineData,
                                color = SolidColor(Color(0xFF2352AF)),
                                firstGradientFillColor = Color.Transparent,
                                secondGradientFillColor = Color.Transparent,
                                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                                gradientAnimationDelay = 1000,

                                drawStyle = DrawStyle.Stroke(width = 1.dp),
                                dotProperties = DotProperties(
                                    radius = 1.dp,
                                    enabled = true,
                                    color = SolidColor(Color.White),
                                    strokeColor = SolidColor(Color(0xFF2352AF)),
                                )
                            )
                        )
                    })
            } else {
                //show Bar chart
                ColumnChart(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    ),
                    gridProperties = GridProperties(
                        enabled = true,
                        xAxisProperties = GridProperties.AxisProperties(enabled = true),
                        yAxisProperties = GridProperties.AxisProperties(
                            enabled = true, color = SolidColor(Color.Transparent)
                        )
                    ),
                    labelProperties = LabelProperties(
                        padding = 2.dp,
                        enabled = true,
                        labels = lineLabels,
                        builder = { modifier, label, shouldRotate, index ->
                            Text(
                                modifier = modifier,
                                text = label,
                                style = MaterialTheme.typography.titleSmall
                            )
                        },
                        rotation = LabelProperties.Rotation(
                            mode = LabelProperties.Rotation.Mode.Force,
                            degree = if (lineData.size > 5) {
                                -45f
                            } else {
                                0f
                            }
                        )

                    ),

                    indicatorProperties = HorizontalIndicatorProperties(
                        enabled = false,
                        position = IndicatorPosition.Horizontal.Start,
                        textStyle = MaterialTheme.typography.labelSmall,

                    ),
                    labelHelperProperties = LabelHelperProperties(enabled = false),
                    data = remember {
                        listOfBarData.map {
                            Bars(
                                label = it.label, values = it.data
                            )
                        }.toList()
                    },
                    barProperties = BarProperties(
                        spacing = 2.dp, thickness = 8.dp
                    ),
                )
            }

        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ShowSwitchableSingleLineAndBar() {
    // SwitchableSingleLineAndBar()
}