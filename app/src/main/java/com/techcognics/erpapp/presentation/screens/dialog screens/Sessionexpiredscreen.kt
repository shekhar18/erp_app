package com.techcognics.erpapp.presentation.screens

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
fun Sessionexpiredscreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAF8FB)), // Light blue background
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.warning), // your warning icon
                contentDescription = "Session Expired",
                tint = Color(0xFFFFC107), // amber color
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Session Expired", style = MaterialTheme.typography.titleMedium)
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun PreviewSessionexpiredscreen() {
    Sessionexpiredscreen()
}