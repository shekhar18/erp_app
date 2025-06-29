package com.techcognics.erpapp.presentation.component.charts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.techcognics.erpapp.presentation.component.text.RichTextLeft

@Composable
fun CardChart(
    title: String,
    amount: String,
    changePercentage: Double,
    isPositive: Boolean,
    barData: List<Double>,
    modifier: Modifier = Modifier,
    color:Color
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background // Replace with your desired color
        ),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = modifier
            .padding(4.dp)
            .background(MaterialTheme.colorScheme.background),
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            SmallBarChart(barData = barData, barColor = color)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "₹$amount",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            RichTextLeft(
                text = "higher vs previous month",
                highlightedText = "${if (isPositive) "" else ""}${changePercentage}% ",
                highlightedTextColor = if (isPositive) Color(0xFF4CAF50) else Color.Red,
                textStyle = MaterialTheme.typography.labelSmall
            ) { }
        }
    }
}

@Composable
fun SmallBarChart(
    barData: List<Double>,
    modifier: Modifier = Modifier,
    barColor: Color = MaterialTheme.colorScheme.primary
) {
    val maxValue = (barData.maxOrNull() ?: 1f).toFloat()

    Row(
        modifier = modifier
            .width(100.dp)
            .height(50.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Absolute.Left
    ) {
        barData.forEach { value ->
            Box(
                modifier = Modifier
                    .width(5.dp)
                    .fillMaxHeight((value.toFloat() / maxValue))
                    .background(barColor, shape = RectangleShape)
            )
            Spacer(modifier = modifier.width(2.dp))
        }
    }
}