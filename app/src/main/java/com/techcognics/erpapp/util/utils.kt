package com.techcognics.erpapp.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

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
            AnnotatedString(transformed),
            OffsetMapping.Identity
        )
    }
}


