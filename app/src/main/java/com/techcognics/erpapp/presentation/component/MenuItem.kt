package com.techcognics.erpapp.presentation.component

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuItem(modifier: Modifier = Modifier, icon: Int, menuTitle: String, itemClick: () -> Unit) {
    val context = LocalContext.current
    Row(modifier = Modifier
        .clickable {
            itemClick()
        }
        .fillMaxWidth()
        .clickable { Toast.makeText(context, menuTitle, Toast.LENGTH_SHORT).show() }
        .padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = modifier
                .height(16.dp)
                .width(16.dp),
            painter = painterResource(icon),
            contentDescription = menuTitle,
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = menuTitle, fontSize = 11.sp)
    }
}