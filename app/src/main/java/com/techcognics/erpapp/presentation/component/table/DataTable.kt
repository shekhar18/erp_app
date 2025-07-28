package com.techcognics.erpapp.presentation.component.table

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.techcognics.erpapp.data.table_data.SortOrder
import com.techcognics.erpapp.data.table_data.SortState
import com.techcognics.erpapp.data.table_data.TableRowData


@Composable
fun DataTable(
    modifier: Modifier = Modifier, headers: List<String>, rows: List<TableRowData>,
    headerColor: Color = Color.LightGray,
) {
    var sortState by remember { mutableStateOf(SortState()) }

    // Sort rows based on state
    val sortedRows = remember(sortState, rows) {
        if (sortState.columnIndex != -1) {
            val index = sortState.columnIndex
            val comparator = compareBy<TableRowData> { it.cells.getOrNull(index) ?: "" }
            when (sortState.order) {
                SortOrder.ASCENDING -> rows.sortedWith(comparator)
                SortOrder.DESCENDING -> rows.sortedWith(comparator.reversed())
            }
        } else rows
    }
    val horizontalScroll = rememberScrollState()
    val verticalScroll = rememberScrollState()

    Column(
        modifier = modifier
            .padding(start = 5.dp, end = 5.dp)
            .horizontalScroll(horizontalScroll)
            .verticalScroll(verticalScroll)
            .border(0.5.dp, Color.Black).clip(RoundedCornerShape(2.dp))
    ) {
        Row(modifier = Modifier.background(headerColor)) {
            headers.forEachIndexed { index, header ->
                val isSorted = index == sortState.columnIndex
                val sortIcon = when {
                    !isSorted -> ""
                    sortState.order == SortOrder.ASCENDING -> " ↑"
                    else -> " ↓"
                }

                Text(text = header + sortIcon, modifier = Modifier
                    .clickable {
                        sortState = if (sortState.columnIndex == index) {
                            sortState.copy(order = sortState.order.toggle())
                        } else {
                            SortState(columnIndex = index, order = SortOrder.ASCENDING)
                        }
                    }
                    .padding(8.dp)
                    .width(120.dp), fontWeight = FontWeight.Bold)
            }
        }

        sortedRows.forEachIndexed { index, row ->
            Row(
                modifier = Modifier.background(if (index % 2 == 0) Color.White else Color(0xFFF5F5F5))
            ) {
                row.cells.forEach { cell ->
                    Text(
                        text = cell, modifier = Modifier
                            .padding(8.dp)
                            .width(120.dp)
                    )
                }
            }
        }
    }
}

private fun SortOrder.toggle(): SortOrder {
    return when (this) {
        SortOrder.ASCENDING -> SortOrder.DESCENDING
        SortOrder.DESCENDING -> SortOrder.ASCENDING
    }
}
