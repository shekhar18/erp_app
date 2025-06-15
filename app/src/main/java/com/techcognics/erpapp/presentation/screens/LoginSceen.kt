package com.techcognics.erpapp.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.techcognics.erpapp.R
import com.techcognics.erpapp.presentation.component.CopyrightFooter
import com.techcognics.erpapp.presentation.component.LoginCardContent

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun LoginScreen(modifier: Modifier = Modifier, navController: NavHostController) {

    val robotoFont = FontFamily(Font(R.font.roboto))
    var userId by remember { mutableStateOf("johndoe1499000") }
    var password by remember { mutableStateOf("123456789") }
    var checked by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize(),
            contentDescription = "Background Image"
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .width(293.dp)
                    .height(341.dp)
                    .border(
                        width = 8.dp,
                        color = colorResource(R.color.card_border_color), // your color
                        shape = RectangleShape // no corner radius
                    ), colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.white) // Light cyan background
                ), content = {

                    LoginCardContent(
                        userId = userId,
                        onUserIdChange = { userId = it },
                        password = password,
                        onPasswordChange = { password = it },
                        checked = checked,
                        onCheckedChange = { checked = it },
                        robotoFont = robotoFont,
                        fontBlue = colorResource(R.color.font_blue_color),
                        navController
                    )
                })
            Spacer(modifier.height(40.dp))
            CopyrightFooter()
        }
    }
}


@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
private fun ShowLoginScreen() {
   // LoginScreen()
}