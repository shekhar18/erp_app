package com.techcognics.erpapp.presentation.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BoarderButton(
    modifier: Modifier = Modifier, onclick: () -> Unit, buttonState: String?, label: String
) {
    TextButton(
        modifier = modifier
            .height(15.dp)
            .width(35.dp),
        contentPadding = PaddingValues(0.dp),
        onClick = { onclick() },
        shape = RoundedCornerShape(20.dp), // Rounded border
        colors = ButtonDefaults.buttonColors(
            containerColor = if (buttonState == label) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background, // Background color
            contentColor = if (buttonState == label) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground // Text color
        ),
        border = BorderStroke(
            2.dp, MaterialTheme.colorScheme.primary
        ) // Optional border
    ) {
        Text(label, style = MaterialTheme.typography.labelSmall)
    }
}