package com.techcognics.erpapp.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.ceil

@Composable
fun SalesComparisonCard(
    modifier: Modifier = Modifier, salseCount: Double, persentage: Double, previousYear: String
) {
    Row(modifier = modifier.padding(start = 10.dp, end = 10.dp)) {
        Card(
            modifier = modifier.height(60.dp),
            shape = RoundedCornerShape(5.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${ceil(salseCount).toInt()} Sales",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                val annotatedText = buildAnnotatedString {


                    pushStringAnnotation(tag = "percentage", annotation = "percentage")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onSecondaryContainer, // Dark Blue
                            fontWeight = FontWeight.W200,
                        )
                    ) {
                        append("${ceil(persentage).toInt()} %")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Gray, // Dark Blue
                            fontWeight = FontWeight.W200
                        )
                    ) { append(" higher vs previous Year ${previousYear}") }
                    pop()
                }

                ClickableText(
                    text = annotatedText, onClick = { offset ->
                        annotatedText.getStringAnnotations(
                            tag = "percentage", start = offset, end = offset
                        ).firstOrNull()?.let {

                        }
                    }, style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}
