package com.techcognics.erpapp.presentation.screens


import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.techcognics.erpapp.R
import com.techcognics.erpapp.presentation.component.CopyrightFooter
import com.techcognics.erpapp.presentation.component.LogoutCardContent

@Composable
fun LogoutScreen(modifier: Modifier = Modifier, navController: NavHostController) {

    val robotoFont = FontFamily(Font(R.font.roboto))

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
                    .height(250.dp), // Adjust height
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.white)
                ),
                content = {
                    LogoutCardContent(
                        robotoFont = robotoFont,
                        fontBlue = colorResource(R.color.font_blue_color),
                        navController = navController
                    )
                }
            )
            Spacer(modifier.height(40.dp))
            CopyrightFooter()
        }
    }
}

@Preview
@Composable
private fun LogoutScreen() {

}




















