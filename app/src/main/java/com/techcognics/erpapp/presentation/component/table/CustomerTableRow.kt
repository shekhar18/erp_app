package com.techcognics.erpapp.presentation.components.table

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.techcognics.erpapp.data.model.Customer

@Composable
fun CustomerTableRow(
    customer: Customer,
    onEdit: () -> Unit = {},     // ✅ Default empty lambda
    onDelete: () -> Unit = {},   // ✅ Default empty lambda
    onView: () -> Unit = {}      // ✅ Default empty lambda
) {
    LazyRow {
        // Displaying fields as columns
        items(listOf(
            customer.bpCode ?: "",
            customer.bpCompanyName,
            customer.contactPerson,
            customer.mobileNo,
            customer.address
        )) { field ->
            if (field != null) {
                Text(field, modifier = Modifier.width(140.dp))
            }
        }
        item {
            Row {
                TextButton(onClick = onEdit) { Text("Edit") }
                TextButton(onClick = onDelete) { Text("Delete") }
                TextButton(onClick = onView) { Text("View") }
            }
        }
    }
}