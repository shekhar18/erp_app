package com.techcognics.erpapp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techcognics.erpapp.data.StatCard

@Composable
fun StatCardItem(card: StatCard, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.8f) // Ensures consistent height-to-width ratio
            .clip(RoundedCornerShape(16.dp))
            .border(2.dp, card.borderColor, RoundedCornerShape(16.dp))
            .background(card.backgroundColor.copy(alpha = 0.2f)),
    ) {

            Image(
                painter = painterResource(id = card.chartRes),
                contentDescription = "Chart",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, top = 10.dp)
            ) {
                Text(text = card.title, fontWeight = FontWeight.Bold, fontSize = 14.sp)


                Row(verticalAlignment = Alignment.Bottom) {
                    Text(text = card.value, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = card.unit, fontSize = 12.sp)
                }


            }



    }
}
