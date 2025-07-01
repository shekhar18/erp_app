package com.techcognics.erpapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DynamicTable(
    modifier: Modifier = Modifier, headers: List<String>, data: List<List<String>>
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val columnWidth = screenWidth / 4

    val horizontalScrollState = rememberScrollState()

    Column(modifier = modifier.fillMaxSize()) {
        Divider(color = Color.Gray, thickness = 1.dp)
        // Sticky Header Row (outside LazyColumn)
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .horizontalScroll(horizontalScrollState)
                .padding(vertical = 8.dp)
        ) {
            headers.forEach { header ->
                Text(
                    text = header,
                    modifier = Modifier
                        .width(columnWidth)
                        .padding(horizontal = 12.dp),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Divider(color = Color.Gray, thickness = 1.dp)

        // LazyColumn with fixed height
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // this avoids infinite height error
        ) {
            itemsIndexed(data) { index, row ->
                if (index % 2 == 0) Color.White else Color(0xFFF7F7F7)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(horizontalScrollState)
                        .padding(vertical = 8.dp)
                ) {
                    row.forEach { cell ->
                        Text(
                            text = cell,
                            modifier = Modifier
                                .width(columnWidth)
                                .padding(horizontal = 12.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                Divider(color = Color.Gray, thickness = 1.dp)
            }
        }
    }
}