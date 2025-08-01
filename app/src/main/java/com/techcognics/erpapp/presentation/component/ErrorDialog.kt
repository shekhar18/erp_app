package com.techcognics.erpapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.techcognics.erpapp.R


@Composable
fun ErrorDialog(modifier: Modifier = Modifier, onClick: () -> Unit, message: String="") {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = modifier
                .height(219.dp)
                .width(252.dp)
                .border(
                    width = 0.dp,
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(26.dp),
                ), elevation = CardDefaults.elevatedCardElevation(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(
                        modifier = Modifier.size(64.dp),
                        onClick = { onClick() },
                        content = {
                            Icon(
                                modifier = modifier,
                                painter = painterResource(id = R.drawable.error_icon),
                                contentDescription = "Error",
                                tint = Color.Red,
                            )
                        },
                    ) // your error icon

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "An Error has occurred. Please try again",
                        style = MaterialTheme.typography.labelSmall,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}