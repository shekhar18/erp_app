package com.techcognics.erpapp.presentation.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.techcognics.erpapp.R

@Composable
fun CustomSwitch(modifier: Modifier = Modifier, isLineChart: Boolean=false,
                 onToggle: (Boolean) -> Unit) {
    val togglePosition by animateDpAsState(
        targetValue = if (isLineChart) 0.dp else 29.dp,
        label = "toggleAnim"
    )

    Box(
        modifier = modifier
            .width(50.dp)
            .height(25.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF3B358D)) // Purple background
            .clickable { onToggle(!isLineChart) }
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        // Movable indicator
        Box(
            modifier = Modifier
                .offset(x = togglePosition)
                .size(15.dp)
                .clip(CircleShape)
                .background(Color.White)
        )

        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.line_icon), // Line chart
                contentDescription = null,
                tint = if (isLineChart) MaterialTheme.colorScheme.primary else Color.White,
                modifier = Modifier.size(10.dp)
            )
            Icon(
                painter = painterResource(R.drawable.bar_icon), // Bar chart
                contentDescription = null,
                tint = if (!isLineChart) MaterialTheme.colorScheme.primary else Color.White,
                modifier = Modifier.size(10.dp)
            )
        }
    }
}

@Preview
@Composable
private fun ShowCustomSwitch() {
    CustomSwitch(){}
}

