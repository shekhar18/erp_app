package com.techcognics.erpapp.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.techcognics.erpapp.presentation.component.inputfields.InputField

@Composable
fun ProfileCardContent(
    FirstName: String,
    onFirstNameChange: (String) -> Unit,
    LastName: String,
    onLastNameChange: (String) -> Unit,
    Email: String,
    onEmailChange: (String) -> Unit,
    fontBlue: Color,
    navController: NavHostController,
    onClick:()-> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(modifier = Modifier.width(192.dp)) {
            InputField("First Name", FirstName, onFirstNameChange)
            Spacer(modifier = Modifier.height(10.dp))
            InputField("Last Name", LastName, onLastNameChange)
            Spacer(modifier = Modifier.height(20.dp))
            InputField("Email", Email, onEmailChange)
            Spacer(modifier = Modifier.height(10.dp))
           
           
        }
    }
}
