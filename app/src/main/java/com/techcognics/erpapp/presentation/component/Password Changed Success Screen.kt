package com.techcognics.erpapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techcognics.erpapp.R

@Composable
fun PasswordChangedSuccessScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAF8FB)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.success), // your success icon
                contentDescription = "Success",
                tint = Color(0xFF4CAF50), // green color
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Congratulations",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF4CAF50)) // Green Text
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "You have successfully changed your password.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}


annotation class Composable

@Preview(showBackground = true)
@Composable
private fun PreviewPasswordChangedSuccessScreen() {
    Sessionexpiredscreen()
}






