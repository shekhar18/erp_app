package com.techcognics.erpapp.util

import android.icu.text.SimpleDateFormat
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.techcognics.erpapp.data.company_dashboard_data.AmountsByMonthResponse
import java.util.Locale

class CustomPasswordTransformation(
    private val dotChar: Char = '◉'
) : VisualTransformation {


    override fun filter(text: AnnotatedString): TransformedText {
        val transformed = buildString {
            repeat(text.length) {
                append(dotChar)
            }
        }
        return TransformedText(
            AnnotatedString(transformed), OffsetMapping.Identity
        )
    }
}


fun extractTableData(input: List<AmountsByMonthResponse>?): List<List<String>> {
    // Group by month
    val groupedByMonth = input?.groupBy { it.month }
    val monthFormat = SimpleDateFormat("MMM-yyyy", Locale.ENGLISH)
    // Convert each month group into a row
    return groupedByMonth?.toSortedMap(compareBy { monthFormat.parse(it) })!!
        .map { (month, entries) ->
            val row = mutableListOf<String>()
            row.add(month) // First column: month

            // Add each value in header order (except Month)
            for (header in Constant.headersOne.drop(1)) {
                val value = entries.find { it.accountName == header }?.totalAmount ?: 0.0
                row.add(value.toString()) // Keep 2 decimal format
            }

            row
        }
}

