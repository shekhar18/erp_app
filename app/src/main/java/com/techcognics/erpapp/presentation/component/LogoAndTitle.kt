package com.techcognics.erpapp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import com.techcognics.erpapp.R


@Composable
fun LogoAndTitle(fontColor: Color) {
    Column(
        modifier = Modifier.padding(top = 10.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .fillMaxWidth()
                .height(49.dp),
            contentDescription = "Logo"
        )

        Text(
            "Enterprise Resource Planning".uppercase(),
            style = MaterialTheme.typography.bodyMedium,
            color = fontColor,
            modifier = Modifier.padding(10.dp),
        )
    }
}/*TextStyle(
                color = fontBlue,
                fontSize = 11.76.sp,
                fontFamily = robotoFont,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 0.08.sp,
            )*/