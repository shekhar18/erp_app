package com.techcognics.erpapp.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomerSearchBar(onSearch: (String) -> Unit) {
    var searchText by remember { mutableStateOf("") }
    Row(Modifier.fillMaxWidth().padding(12.dp)) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search Customer") },
            modifier = Modifier.weight(1f)
        )
        Spacer(Modifier.width(8.dp))
        OutlinedButton(onClick = { onSearch(searchText) }) { Text("Apply") }
        Spacer(Modifier.width(8.dp))
        OutlinedButton(onClick = { searchText = ""; onSearch("") }) { Text("Reset") }
    }
}
