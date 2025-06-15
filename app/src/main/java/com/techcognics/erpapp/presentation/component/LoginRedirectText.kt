package com.techcognics.erpapp.presentation.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.techcognics.erpapp.R

@Composable
fun LoginRedirectText() {
    val context = LocalContext.current

    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = colorResource(R.color.black), // Dark Blue
                fontWeight = FontWeight.W500
            )
        ){append("If you already have an account ? ")}

        pushStringAnnotation(tag = "LOGIN", annotation = "login")
        withStyle(
            style = SpanStyle(
                color = colorResource(R.color.login_here_text_color), // Dark Blue
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        ) {
            append("Login Here")
        }
        pop()
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            annotatedText.getStringAnnotations(tag = "LOGIN", start = offset, end = offset)
                .firstOrNull()?.let {

                }
        },
        style = TextStyle(
            fontSize = 12.sp,
            color = Color.Black,
            textAlign = TextAlign.Start
        )
    )
}
