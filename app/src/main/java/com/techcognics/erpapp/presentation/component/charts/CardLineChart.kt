package com.techcognics.erpapp.presentation.component.charts


import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DividerProperties
import ir.ehsannarmani.compose_charts.models.DotProperties
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.GridProperties
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.IndicatorPosition
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.Line
import ir.ehsannarmani.compose_charts.models.LineProperties

@Composable
fun CardLineChart(
    label: String = "00.00",
    lineCurve: Boolean = true,
    modifier: Modifier = Modifier,
    dataList: List<Double> = emptyList<Double>(),
    firstGradientFillColor: Color,
    secondGradientFillColor: Color,
    lineThickness: Dp
) {
    Box {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            shape = RoundedCornerShape(4.dp),
            modifier = modifier
                .padding(4.dp)
                .height(160.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background).shadow(
                    elevation = if (isSystemInDarkTheme()) 10.dp else 8.dp,
                    ambientColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.5f) else Color.Black.copy(alpha = 0.5f),
                    spotColor = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.5f) else Color.Black.copy(alpha = 0.5f)
                ),
        ) {
            Column(
                modifier = Modifier.padding(
                    top = 70.dp,
                   /* start = 5.dp,
                    end = 5.dp,
                    bottom = 5.dp*/
                )
            ) {
                LineChart(
                    curvedEdges = lineCurve,
                    indicatorProperties = HorizontalIndicatorProperties(
                        enabled = false, position = IndicatorPosition.Horizontal.Start,
                    ),
                    dividerProperties = DividerProperties(
                        enabled = false,
                        xAxisProperties = LineProperties(enabled = false),
                        yAxisProperties = LineProperties(enabled = false)
                    ),
                    labelHelperProperties = LabelHelperProperties(enabled = false),
                    gridProperties = GridProperties(enabled = false),
                    modifier = Modifier.fillMaxSize(),
                    data = remember {
                        listOf(
                            Line(
                                label = "",
                                values = dataList,
                                color = SolidColor(firstGradientFillColor),
                                firstGradientFillColor = firstGradientFillColor.copy(0.5f),
                                secondGradientFillColor = secondGradientFillColor.copy(-0.5f),
                                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                                gradientAnimationDelay = 1000,
                                drawStyle = DrawStyle.Stroke(width = lineThickness),
                                /*dotProperties = DotProperties(
                                    enabled = true,
                                    color = SolidColor(Color.White),
                                    strokeColor = SolidColor(firstGradientFillColor),
                                )*/
                            )
                        )
                    },
                    animationMode = AnimationMode.Together(delayBuilder = {
                        it * 500L
                    })
                )
            }
        }
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.W900,
            modifier = modifier.padding(top = 15.dp, start = 20.dp)
        )
    }


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ShowCardLineChart() {
    CardLineChart(
        "11,756",
        dataList = listOf(28.0, 41.0, 5.0, 10.0, 35.0),
        modifier = Modifier,
        firstGradientFillColor = Color(0xFF2F4C96).copy(alpha = .5f),
        secondGradientFillColor = Color.Transparent,
        lineThickness = 1.dp,
    )
}