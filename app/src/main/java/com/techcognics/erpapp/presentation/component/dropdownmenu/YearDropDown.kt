package com.techcognics.erpapp.presentation.component.dropdownmenu

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.techcognics.erpapp.data.sales_dashboard_data.FiscalYearOfSalesResponse

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun YearDropdown(
    modifier: Modifier = Modifier,
    years: List<FiscalYearOfSalesResponse>?,
    onYearSelected: (String) -> Unit,
    currentYear: String?
) {
    var expanded by remember { mutableStateOf(false) }


    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        ExposedDropdownMenuBox(
            modifier = modifier
                .clip(RoundedCornerShape(50)) // Capsule shape
                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(50))
                .background(MaterialTheme.colorScheme.background)
                .height(56.dp)
                .width((LocalConfiguration.current.screenWidthDp / 2.5).dp),
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }) {
            TextField(
                value = currentYear?.toString() ?: "",
                onValueChange = {},
                readOnly = true,
                label = { Text("Fiscal Year", style = MaterialTheme.typography.labelLarge) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true)
            )
            ExposedDropdownMenu(
                expanded = expanded, onDismissRequest = { expanded = false }) {
                years?.forEach { year ->
                    DropdownMenuItem(text = {
                        Text(
                            year.fiscalYear.toString(), style = MaterialTheme.typography.labelSmall
                        )
                    }, onClick = {

                        expanded = false
                        onYearSelected(year.fiscalYear.toString())
                    })
                }
            }
        }
    }


}