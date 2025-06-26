package com.techcognics.erpapp.presentation.component.dropdownmenu

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomDropdownMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    menuItems: List<String>,
    onItemClick:  (String) -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier,

    ) {
        menuItems.forEach { item ->
            DropdownMenuItem(text = { Text(text = item) }, onClick = {
                onDismissRequest
                onItemClick(item)
            }


            )
        }
    }
}