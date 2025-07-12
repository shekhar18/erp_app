package com.techcognics.erpapp.presentation.component.charts

//import com.techcognics.erpapp.util.showTooltipOnClick
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.techcognics.erpapp.data.MainData
import com.techcognics.erpapp.data.sales_dashboard_data.FetchItemGroupDetailsPercentageResponse
import com.techcognics.erpapp.data.sales_dashboard_data.StateWiseSalesInvoiceDetailsResponse
import com.techcognics.erpapp.presentation.component.TooltipWithArrow
import com.techcognics.erpapp.util.getRandomColor
import com.techcognics.erpapp.util.listenTapOffset
import ir.ehsannarmani.compose_charts.PieChart
import ir.ehsannarmani.compose_charts.models.Pie


@Composable
fun Pie(
    modifier: Modifier = Modifier, data: List<MainData>?
) {
    val pieList = data?.takeIf { it.isNotEmpty() }?.mapNotNull {
        when (it) {
            is FetchItemGroupDetailsPercentageResponse -> {
                Pie(
                    data = it.percentage, color = getRandomColor(), label = it.itemGroupName
                )
            }

            is StateWiseSalesInvoiceDetailsResponse -> {
                Pie(
                    data = it.percentage, color = getRandomColor(), label = it.stateName
                )
            }

            else -> null
        }
    }

    var list by rememberSaveable { mutableStateOf(pieList) }

    val OffsetSaver: Saver<Offset, List<Float>> =
        Saver(save = { listOf(it.x, it.y) }, restore = { Offset(it[0], it[1]) })

    var offset by rememberSaveable(stateSaver = OffsetSaver) { mutableStateOf(Offset.Zero) }
    var showToolTip by rememberSaveable { mutableStateOf(false) }

    val initialPieValue = Pie(
        data = 0.0, label = "", color = Color.Gray
    )
    var pieValues: Pie by remember { mutableStateOf(initialPieValue) }

    // Full screen Box to detect taps outside PieChart
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    showToolTip = false
                })
            }) {
        // Tooltip
        if (showToolTip) {
            TooltipWithArrow(
                text = "${pieValues.label} - ${pieValues.data}%", position = offset
            )
        }

        // Chart and legends
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(0f),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = modifier.height(25.dp))

                if (data?.isEmpty() == true) {
                    Text(
                        "No Data Found",
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                } else {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.size(160.dp)) {
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .listenTapOffset {
                                        offset = it
                                    })
                            PieChart(
                                modifier = Modifier.size(120.dp),
                                data = remember {
                                    list.orEmpty().map {
                                        Pie(
                                            label = it.label, data = it.data, color = it.color
                                        )
                                    }
                                },
                                onPieClick = { clickedPie ->
                                    pieValues = clickedPie
                                    list = list?.map {
                                        it.copy(selected = it == clickedPie)
                                    }
                                    showToolTip = true
                                },
                                selectedScale = 1.2f,
                                scaleAnimEnterSpec = spring(
                                    dampingRatio = Spring.DampingRatioNoBouncy,
                                    stiffness = Spring.StiffnessLow
                                ),
                                colorAnimEnterSpec = tween(300),
                                colorAnimExitSpec = tween(300),
                                scaleAnimExitSpec = tween(300),
                                spaceDegreeAnimExitSpec = tween(300),
                                selectedPaddingDegree = 4f,
                                style = Pie.Style.Stroke(width = 20.dp)
                            )
                        }
                        // Legends
                        Box(modifier = modifier.padding(16.dp)) {
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                list?.forEach { item ->
                                    Legends(
                                        modifier = modifier,
                                        color = item.color,
                                        text = item.label.toString()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun ShowPie() {
    // Pie()
}