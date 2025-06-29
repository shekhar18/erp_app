package com.techcognics.erpapp.presentation.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun DashboardScreen(modifier: Modifier = Modifier, homeNavController: NavHostController) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to ERP", style = MaterialTheme.typography.bodyLarge)
        Text(
            text = "You are Logged in as ${"Admin"}",
            style = MaterialTheme.typography.bodySmall,
            modifier = modifier
                .background(
                    MaterialTheme.colorScheme.outline
                )
                .padding(start = 10.dp, end = 10.dp)
        )
    }
}