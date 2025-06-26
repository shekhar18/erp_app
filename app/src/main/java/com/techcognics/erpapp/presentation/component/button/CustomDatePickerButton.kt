package com.techcognics.erpapp.presentation.component.button



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CustomDateRangeButton() {
    var showDateRangeDialog by remember { mutableStateOf(false) }
    var selectedStartDate by remember { mutableStateOf<Long?>(null) }
    var selectedEndDate by remember { mutableStateOf<Long?>(null) }

    val formatter = remember {
        SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    }

    val rangeText = if (selectedStartDate != null && selectedEndDate != null) {
        "${formatter.format(Date(selectedStartDate!!))} → ${formatter.format(Date(selectedEndDate!!))}"
    } else {
        "Custom"
    }

    val buttonLabel = "Period: $rangeText ⌄"

    Column(modifier = Modifier.padding(16.dp)) {
        // 🌟 Period Button with dropdown arrow
        Button(
            onClick = { showDateRangeDialog = true },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = buttonLabel,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // 📅 Show Date Picker Dialog
        if (showDateRangeDialog) {
            DateRangePickerModal(
                onDateRangeSelected = { range ->
                    selectedStartDate = range.first
                    selectedEndDate = range.second
                    showDateRangeDialog = false
                },
                onDismiss = { showDateRangeDialog = false }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
    onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
    onDismiss: () -> Unit
) {
    val dateRangePickerState = rememberDateRangePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateRangeSelected(
                        Pair(
                            dateRangePickerState.selectedStartDateMillis,
                            dateRangePickerState.selectedEndDateMillis
                        )
                    )
                    onDismiss()
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DateRangePicker(
            state = dateRangePickerState,
            title = {
                Text(
                    text = "Select date range"
                )
            },
            showModeToggle = false,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true, name = "Custom Date Range Bi",
    device = "spec:width=1080px,height=2340px,dpi=440,isRound=true",
    wallpaper = Wallpapers.NONE
)
@Composable
private fun CustomDateRangeButtonPreview() {
    MaterialTheme {
        CustomDateRangeButton()
    }
}

