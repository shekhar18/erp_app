package com.techcognics.erpapp.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Loader(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = modifier
                .height(50.dp)
                .width(120.dp),
            shape = RoundedCornerShape(4.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp,
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 2.dp,
                    modifier = modifier
                        .height(25.dp)
                        .width(25.dp)
                )
                Spacer(modifier = modifier.width(10.dp))
                Text(text = "Loading", style = MaterialTheme.typography.bodySmall)
            }

        }
    }

}

@Preview(
    device = "spec:width=1080px,height=2340px,dpi=440",
    showSystemUi = true,
    showBackground = true
)
@Composable
private fun ShowLoader() {
    Loader()
}