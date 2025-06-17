package com.techcognics.erpapp.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun VerticalStageStepper(
    stages: List<String>, modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(start = 30.dp)) {
        stages.forEachIndexed { index, stage ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Canvas(
                    modifier = Modifier.size(width = 24.dp, height = 30.dp)
                ) {
                    val centerX = size.width / 2
                    val dotRadius = 6.dp.toPx()
                    if (index != 0) {
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(centerX, 0f),
                            end = Offset(centerX, size.height / 2 - dotRadius),
                            strokeWidth = 4f
                        )
                    }
                    drawCircle(
                        color = Color.LightGray,
                        radius = dotRadius,
                        center = Offset(centerX, size.height / 2)
                    )
                    if (index != stages.lastIndex) {
                        drawLine(
                            color = Color.LightGray,
                            start = Offset(centerX, size.height / 2f + dotRadius),
                            end = Offset(centerX, size.height),
                            strokeWidth = 4f
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = stage, color = Color.Gray, fontSize = 11.sp
                )
            }
        }
    }
}
