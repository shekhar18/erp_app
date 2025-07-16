package com.techcognics.erpapp.util

import android.R.attr.onClick
import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.changedToDown
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.techcognics.erpapp.data.BarDataCard
import com.techcognics.erpapp.data.RowBarData
import com.techcognics.erpapp.data.company_dashboard_data.AmountsByMonthResponse
import ir.ehsannarmani.compose_charts.models.Bars
import java.util.Locale
import kotlin.random.Random


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

fun Modifier.listenTapOffset(
    onTap: (Offset) -> Unit
): Modifier = pointerInput(Unit) {
    awaitPointerEventScope {
        while (true) {
            val event = awaitPointerEvent()
            val down = event.changes.firstOrNull { it.changedToDown() }
            if (down != null) {
                onTap(down.position)
            }
        }
    }
}

fun getRandomColor(): Color {
    return Color(
        red = Random.nextFloat(),
        green = Random.nextFloat(),
        blue = Random.nextFloat(),
        alpha = 1f // Full opacity
    )
}

fun <T> List<T>.toRowBarData(mapper: (T) -> Pair<String, Double>): List<RowBarData> {
    if (isEmpty()) return emptyList()
    return listOf(
        RowBarData(
            barListDate = this.map { item ->
                val (label, value) = mapper(item)
                BarDataCard(
                    label = label,
                    data = listOf(
                        Bars.Data(
                            value = value,
                            label = label,
                            color = SolidColor(getRandomColor())
                        )
                    )
                )
            },
            color = getRandomColor()
        )
    )
}

