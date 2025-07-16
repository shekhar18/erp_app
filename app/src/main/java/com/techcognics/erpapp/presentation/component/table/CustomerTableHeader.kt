package com.techcognics.erpapp.presentation.component.table

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomerTableHeader() {
    LazyRow(Modifier.fillMaxWidth().padding(8.dp)) {
        items(listOf("Code", "Company", "Contact", "Mobile", "City", "Action")) { header ->
            Text(header, Modifier.width(140.dp), fontWeight = FontWeight.Bold)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun CustomerTableHeaderPreview() {
    CustomerTableHeader()
}



