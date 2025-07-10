package com.techcognics.erpapp.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun TooltipWithArrow(
    modifier: Modifier = Modifier, text: String, position: Offset
) {
    Box(modifier = modifier
        .offset {
            // Offset tooltip slightly from touch point
            IntOffset(
                (position.x + 16).toInt().coerceAtLeast(0),
                (position.y - 60).toInt().coerceAtLeast(0)
            )
        }
        .zIndex(2f)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Tooltip Box
            Box(
                modifier = Modifier
                    .background(Color.White, shape = MaterialTheme.shapes.small)
                    .border(1.dp, Color.LightGray, MaterialTheme.shapes.small)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = text, style = MaterialTheme.typography.labelMedium, color = Color.Black
                )
            }
            // Small triangle arrow pointing down
            Canvas(modifier = Modifier.size(12.dp)) {
                drawPath(
                    path = Path().apply {
                        moveTo(0f, 0f)
                        lineTo(size.width / 2, size.height)
                        lineTo(size.width, 0f)
                        close()
                    }, color = Color.White
                )
                drawPath(
                    path = Path().apply {
                        moveTo(0f, 0f)
                        lineTo(size.width / 2, size.height)
                        lineTo(size.width, 0f)
                        close()
                    }, color = Color.LightGray, style = Stroke(width = 1f)
                )
            }
        }
    }
}