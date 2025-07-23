package com.techcognics.erpapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ScreenTitle(modifier: Modifier = Modifier, titleFirst: String, titleSecond: String = "") {
    Row(
        modifier = modifier
            .height(30.dp)
            .padding(start = 10.dp, top = 5.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = titleFirst,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(
            modifier = modifier
                .width(8.dp)
                .fillMaxHeight()
        )

        if (titleSecond.isNotEmpty()) {
            Spacer(
                modifier = modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(Color.Gray)
            )
            Spacer(
                modifier = modifier
                    .width(8.dp)
                    .fillMaxHeight()
            )
            Text(
                text = titleSecond,// "Overview analysis",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(
            modifier = modifier
                .width(8.dp)
                .fillMaxHeight()
        )
    }
}