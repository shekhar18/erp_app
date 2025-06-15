package com.techcognics.erpapp.presentation.screens

import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.techcognics.erpapp.R
import com.techcognics.erpapp.presentation.component.CopyrightFooter
import com.techcognics.erpapp.presentation.component.RegistrationInputField
import com.techcognics.erpapp.presentation.component.RegistrationTitle
import com.techcognics.erpapp.presentation.component.SubmitButton

@Composable
fun RegistrationScreen(modifier: Modifier = Modifier, navController: NavController) {
    val robotoFont = FontFamily(Font(R.font.roboto))
    val context = LocalContext.current
    val scrollState = rememberScrollState()
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
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .width(294.dp)
                    .height(341.dp)
                    .border(
                        width = 4.dp, color = colorResource(R.color.white), shape = RectangleShape
                    ), colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.white)
                ), elevation = CardDefaults.elevatedCardElevation(10.dp), content = {
                    RegistrationTitle(robotoFont = robotoFont)
                    Spacer(modifier = modifier.height(14.dp))
                    RegistrationInputField(
                        icon = Icons.Outlined.MailOutline,
                        contentDescription = "Email",
                        isPasswordField = false,
                        hintText = "E Mail",
                        keypadType = KeyboardType.Email
                    )
                    Spacer(modifier = modifier.height(5.dp))
                    RegistrationInputField(
                        icon = Icons.Outlined.Lock,
                        contentDescription = "Password",
                        isPasswordField = true,
                        hintText = "Password",
                        keypadType = KeyboardType.Password
                    )
                    Spacer(modifier = modifier.height(5.dp))
                    RegistrationInputField(
                        icon = Icons.Outlined.Person,
                        contentDescription = "Company Name",
                        isPasswordField = false,
                        hintText = "Company Name",
                        keypadType =  KeyboardType.Text
                    )
                    Spacer(modifier = modifier.height(5.dp))
                    RegistrationInputField(
                        icon = Icons.Outlined.AccountBox,
                        contentDescription = "Contact Person",
                        isPasswordField = false,
                        hintText = "Contact Person",
                        keypadType =  KeyboardType.Text
                    )
                    Spacer(modifier = modifier.height(5.dp))
                    RegistrationInputField(
                        icon = Icons.Outlined.LocationOn,
                        contentDescription = "Country",
                        isPasswordField = false,
                        hintText = "Country",
                        keypadType =  KeyboardType.Text
                    )
                    Spacer(modifier = modifier.height(5.dp))
                    RegistrationInputField(
                        icon = Icons.Outlined.Call,
                        contentDescription = "Mobile Number",
                        isPasswordField = false,
                        hintText = "Mobile Number",
                        keypadType =  KeyboardType.Phone
                    )
                    Spacer(modifier = modifier.height(5.dp))
                    RegistrationInputField(
                        icon = Icons.Outlined.AccountCircle,
                        contentDescription = "Supplier",
                        isPasswordField = false,
                        hintText = "Supplier",
                        keypadType =  KeyboardType.Text
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    SubmitButton {
                        Toast.makeText(context, "Submit Data", Toast.LENGTH_SHORT).show()
                    }
                })
            Spacer(modifier.height(40.dp))
            CopyrightFooter()
        }
    }
}

@Preview
@Composable
private fun ShowRegistrationScreen() {
    //RegistrationScreen()

}