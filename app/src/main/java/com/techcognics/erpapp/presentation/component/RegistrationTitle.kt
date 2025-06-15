package com.techcognics.erpapp.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegistrationTitle(modifier: Modifier = Modifier, robotoFont: FontFamily) {
    Column(
        modifier = modifier.fillMaxWidth().padding(top = 10.dp, start = 10.dp), horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Registration", fontFamily = robotoFont, fontWeight = FontWeight.W900, fontSize = 20.sp
        )

        LoginRedirectText()
    }
}