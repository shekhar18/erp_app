package com.techcognics.erpapp.presentation.component

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.techcognics.erpapp.R
import com.techcognics.erpapp.presentation.screens.company_dashboard.CompanyDashboardScreenViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateRangePicker(
    onDateRangeSelected: (LocalDate, LocalDate) -> Unit = { _, _ -> },
    viewModel: CompanyDashboardScreenViewModel
) {

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val context = LocalContext.current


    val today = remember { LocalDate.now() }
    val previousMonth = remember { today.minusMonths(1) }

    var startDate by remember { mutableStateOf(previousMonth) }
    var endDate by remember { mutableStateOf(today) }
    var step by remember { mutableStateOf(1) }


    val startDatePickerDialog = DatePickerDialog(
        context,
        R.style.CustomCalendarTheme,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            startDate = LocalDate.of(year, month + 1, dayOfMonth)
            if (startDate.isAfter(endDate)) {
                endDate = startDate.plusDays(1)
            }
            step = 2

            Toast.makeText(context, "Please Select end date.", Toast.LENGTH_SHORT).show()
        },
        startDate.year,
        startDate.monthValue - 1,
        startDate.dayOfMonth
    )

    val endDatePickerDialog = DatePickerDialog(
        context,
        R.style.CustomCalendarTheme,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            endDate = LocalDate.of(year, month + 1, dayOfMonth)
            if (endDate.isBefore(startDate)) {
                startDate = endDate.minusDays(1)
            }
            onDateRangeSelected(startDate, endDate)
            step = 1

        },
        endDate.year,
        endDate.monthValue - 1,
        endDate.dayOfMonth
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        viewModel.updateStartDate(startDate = startDate.toString())
        viewModel.updateEndDate(endDate = endDate.toString())
        Icon(
            painter = painterResource(R.drawable.filter_icon),
            contentDescription = "Filter Icon",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "Filters :",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.width(6.dp))

        Surface(
            shape = RoundedCornerShape(25),
            color = Color(0xFFD0CCF6),
            modifier = Modifier
                .clickable {
                    when (step) {
                        1 -> startDatePickerDialog.show()
                        2 -> endDatePickerDialog.show()
                    }
                }
                .padding(horizontal = 4.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 4.dp)
            ) {
                Text(
                    text = "Period : ${startDate.format(formatter)} to ${endDate.format(formatter)}",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown",
                    tint = Color.Black,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

    }
}