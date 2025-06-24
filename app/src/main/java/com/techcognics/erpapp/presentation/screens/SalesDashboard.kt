package com.techcognics.erpapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.techcognics.erpapp.R
import com.techcognics.erpapp.data.StatCard
import com.techcognics.erpapp.presentation.component.SalesComparisonCard
import com.techcognics.erpapp.presentation.component.StatCardItem
import com.techcognics.erpapp.presentation.component.charts.Pie

@Composable
fun SalesDashboard(modifier: Modifier = Modifier, paddingValue: PaddingValues) {

    val salesState = listOf(
        StatCard(
            "Revenue", "₹50", "Cr", Color(0xFFFFCDD2), Color(0xFFFFEBEE), R.drawable.rad_chart
        ), StatCard(
            "Expected Revenue",
            "₹13",
            "Lakhs",
            Color(0xFFC8E6C9),
            Color(0xFFE8F5E9),
            R.drawable.green_chart
        ), StatCard(
            "Sales Orders",
            "₹12",
            "Lakhs",
            Color(0xFFD1C4E9),
            Color(0xFFEDE7F6),
            R.drawable.purple_chart
        ), StatCard(
            "All Values",
            "₹12",
            "Lakhs",
            Color(0xFFFFE0B2),
            Color(0xFFFFF3E0),
            R.drawable.yellow_chart
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValue)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Techcognics India Pvt LTD Sales Dashboard",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(8.dp),
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(salesState.size) { index ->
                StatCardItem(
                    card = salesState[index],
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.6f) // adjust ratio to fit nicely
                )
            }
        }
        SalesComparisonCard()
        Spacer(modifier = modifier.height(10.dp))
        Card(
            modifier = modifier
                .height(400.dp)
                .width(350.dp),
            shape = RoundedCornerShape(4.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp,
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Pie()
        }
    }
}