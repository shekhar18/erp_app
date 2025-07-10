package com.techcognics.erpapp.presentation.component.charts

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techcognics.erpapp.data.sales_dashboard_data.StateWiseSalesInvoiceDetailsResponse
import com.techcognics.erpapp.presentation.component.button.BoarderButton
import com.techcognics.erpapp.util.getRandomColor
import ir.ehsannarmani.compose_charts.PieChart
import ir.ehsannarmani.compose_charts.models.Pie


@Composable
fun Pie(
    modifier: Modifier = Modifier,
    data: List<StateWiseSalesInvoiceDetailsResponse>?,
    onclick: (String) -> Unit,
    buttonState: String?
) {
    var list: List<Pie>? = emptyList()

    list = data?.takeIf { it.isNotEmpty() }?.map {
        Pie(
            data = it.percentage, color = getRandomColor(), label = it.stateName
        )
    }

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, top = 10.dp, end = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Salse(Amt) By State",
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W600,
                    color = MaterialTheme.colorScheme.primary
                )
                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    BoarderButton(
                        modifier = modifier,
                        onclick = {
                            onclick("1M")
                        },
                        label = "1M",
                        buttonState = buttonState,
                    )
                    Spacer(modifier = modifier.height(5.dp))
                    BoarderButton(
                        modifier = modifier,
                        onclick = {
                            onclick("3M")
                        },
                        label = "3M",
                        buttonState = buttonState,
                    )
                    Spacer(modifier = modifier.height(5.dp))
                    BoarderButton(
                        modifier = modifier,
                        onclick = {
                            onclick("6M")
                        },
                        label = "6M",
                        buttonState = buttonState,
                    )
                    Spacer(modifier = modifier.height(5.dp))
                    BoarderButton(
                        modifier = modifier, buttonState = buttonState, label = "1Y", onclick = {
                            onclick("1Y")
                        })

                }
            }
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
                    PieChart(
                        modifier = Modifier.size(130.dp),
                        data = list!!,
                        onPieClick = {
                            println("${it.label} Clicked")
                            val pieIndex = list?.indexOf(it)
                            list =
                                list?.mapIndexed { mapIndex, pie -> pie.copy(selected = pieIndex == mapIndex) }

                        },
                        selectedScale = 1.2f,
                        scaleAnimEnterSpec = spring<Float>(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        ),
                        colorAnimEnterSpec = tween(300),
                        colorAnimExitSpec = tween(300),
                        scaleAnimExitSpec = tween(300),
                        spaceDegreeAnimExitSpec = tween(300),
                        selectedPaddingDegree = 4f,
                        style = Pie.Style.Stroke(width = 20.dp)
                    )
                    Box(
                        modifier = modifier.padding(16.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
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