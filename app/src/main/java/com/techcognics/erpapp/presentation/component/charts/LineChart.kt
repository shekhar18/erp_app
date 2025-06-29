package com.techcognics.erpapp.presentation.component.charts


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.techcognics.erpapp.presentation.component.text.RichTextLeft
import ir.ehsannarmani.compose_charts.LineChart

@Composable
fun LineChartCard(modifier: Modifier = Modifier, /*dataPoints: List<Float>,
              lineColor: Color = Color(0xFF2962FF),
              fillColor: Color = Color(0x332962FF)*/) {

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
            //LineChart()
        }
    }

}