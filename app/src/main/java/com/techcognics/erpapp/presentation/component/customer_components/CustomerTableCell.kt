package com.techcognics.erpapp.presentation.component.customer_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp


@Composable
fun CustomerTableCell(text: String, width: Dp = 140.dp, bold: Boolean = false){
    Box {
        Text(
            text = text,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier
                .width(width)
                .padding(8.dp)

        )
    }

}

















