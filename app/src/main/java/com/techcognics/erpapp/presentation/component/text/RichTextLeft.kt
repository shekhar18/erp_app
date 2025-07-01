package com.techcognics.erpapp.presentation.component.text

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle

@Composable

fun RichTextLeft(
    text: String,
    highlightedText: String,
    color: Color = Color.Black,
    highlightedTextColor: Color = Color.Black,
    underline: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge,
    onclick: () -> Unit,
) {
    val annotatedText = buildAnnotatedString {
        pushStringAnnotation(tag = "MAINTEXT", annotation = "maintext")
        withStyle(
            style = SpanStyle(
                color = highlightedTextColor, // Dark Blue
                textDecoration = if (underline) TextDecoration.Underline else TextDecoration.None,
                fontWeight = FontWeight.SemiBold,
            )
        ) {
            append(highlightedText)
        }
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onBackground, // Dark Blue
                fontWeight = FontWeight.W500
            )
        ) { append(text) }
        pop()
    }

    ClickableText(
        text = annotatedText, onClick = { offset ->
            annotatedText.getStringAnnotations(tag = "MAINTEXT", start = offset, end = offset)
                .firstOrNull()?.let {
                    onclick()
                }
        }, style = textStyle
    )
}