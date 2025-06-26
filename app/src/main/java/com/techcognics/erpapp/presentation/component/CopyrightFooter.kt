package com.techcognics.erpapp.presentation.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CopyrightFooter(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.White,
            )
        ) { append("Copyright © 2025 ")}

        pushStringAnnotation(tag = "URL", annotation = "https://techcognicsindia.com")
        withStyle(
            style = SpanStyle(
                color = Color.White, textDecoration = TextDecoration.Underline
            )
        ) {
            append("techcognicsindia.com")
        }
        pop()
        withStyle(
            style = SpanStyle(
                color = Color.White,
            )
        ) {
        append(" -\nAll rights reserved.")}
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ClickableText(
            text = annotatedString, style = MaterialTheme.typography.bodyMedium, onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                    .firstOrNull()?.let { annotation ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                        context.startActivity(intent)
                    }
            })
    }
}
