package com.techcognics.erpapp.presentation.component.charts

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.ehsannarmani.compose_charts.PieChart
import ir.ehsannarmani.compose_charts.models.Pie


@Composable
fun Pie(modifier: Modifier = Modifier) {

    var data by remember {
        mutableStateOf(
            listOf(
                Pie(
                    label = "Goa", data = 12.0, color = Color(0xFF9387FF)
                ),
                Pie(
                    label = "Gujarat", data = 8.0, color = Color(0xFF2B2A38)
                ),
                Pie(
                    label = "Haryana", data = 15.0, color = Color(0xFF3D3B9D)
                ),
                Pie(
                    label = "Himachal Pradesh", data = 10.0, color = Color(0xFF9F9ED4)
                ),
                Pie(
                    label = "Manipur", data = 7.0, color = Color(0xFF6369AD)
                ),
                Pie(
                    label = "Jharkhand", data = 9.0, color = Color(0xFF6BA2FB)
                ),
                Pie(
                    label = "Karnataka", data = 11.0, color = Color(0xFF09306F)
                ),
                Pie(
                    label = "Kerala", data = 6.0, color = Color(0xFF1300C1)
                ),
                Pie(
                    label = "Maharashtra", data = 14.0, color = Color(0xFF9387FF)
                ),
                Pie(
                    label = "Rajasthan", data = 8.0, color = Color(0xFF1846FF)
                ),

                )
        )
    }


    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Sales(Amount) by State", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = modifier.height(10.dp))

            PieChart(
                modifier = Modifier.size(150.dp),
                data = data,
                onPieClick = {
                    println("${it.label} Clicked")
                    val pieIndex = data.indexOf(it)
                    data =
                        data.mapIndexed { mapIndex, pie -> pie.copy(selected = pieIndex == mapIndex) }
                },
                selectedScale = 1.2f,
                scaleAnimEnterSpec = spring<Float>(
                    dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow
                ),
                colorAnimEnterSpec = tween(300),
                colorAnimExitSpec = tween(300),
                scaleAnimExitSpec = tween(300),
                spaceDegreeAnimExitSpec = tween(300),
                selectedPaddingDegree = 4f,
                style = Pie.Style.Stroke(width = 25.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        data.take(5).forEach { item ->
                            Legends(
                                modifier = modifier,
                                color = item.color,
                                text = item.label.toString()
                            )
                        }
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        data.takeLast(5).forEach { item ->
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

@Preview
@Composable
private fun ShowPie() {
    Pie()
}