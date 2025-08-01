package com.techcognics.erpapp.presentation.screens.registration

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.techcognics.erpapp.R
import com.techcognics.erpapp.presentation.base.Result
import com.techcognics.erpapp.presentation.component.CopyrightFooter
import com.techcognics.erpapp.presentation.component.Loader
import com.techcognics.erpapp.presentation.component.button.PrimaryButton
import com.techcognics.erpapp.presentation.component.inputfields.IconInputField
import com.techcognics.erpapp.presentation.component.text.RichTextRight
import com.techcognics.erpapp.presentation.component.text.TitleText

@Composable
fun RegistrationScreen(modifier: Modifier = Modifier, mainNavController: NavHostController) {
    FontFamily(Font(R.font.roboto))

    val scrollState = rememberScrollState()

    val viewModel: RegistrationViewModel = hiltViewModel()
    val registrationState = viewModel.registrationState.observeAsState().value


    when (registrationState) {
        is Result.Loading -> {
            Loader()
        }

        is Result.Success<*> -> {
            Toast.makeText(LocalContext.current, "Account Create successfully.", Toast.LENGTH_SHORT)
                .show()
            mainNavController.popBackStack()

        }

        is Result.Error -> {
            Toast.makeText(
                LocalContext.current, registrationState.message, Toast.LENGTH_SHORT
            ).show()
            viewModel.reset()
        }

        is Result.Idle -> {
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
                                width = 4.dp,
                                color = MaterialTheme.colorScheme.background,
                                shape = RectangleShape
                            ), colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background
                        ), elevation = CardDefaults.elevatedCardElevation(10.dp), content = {


                            Row(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp, start = 10.dp)
                            ) { TitleText("Registration") }
                            //RegistrationTitle(robotoFont = robotoFont)
                            Row(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(top = 5.dp, start = 10.dp)
                            ) {
                                RichTextRight(
                                    "If you already have an account ? ",
                                    "Login Here",
                                    MaterialTheme.colorScheme.onSurface,
                                    MaterialTheme.colorScheme.primary,
                                    onclick = {
                                        mainNavController.popBackStack()
                                    })
                            }
                            Spacer(modifier = modifier.height(12.dp))
                            IconInputField(
                                modifier = modifier,
                                value = viewModel.email.observeAsState().value.toString(),
                                onValueChange = { viewModel.updateEmail(it) },
                                icon = R.drawable.email_icon,
                                contentDescription = "Email",
                                isPasswordField = false,
                                hintText = "E Mail",
                                keypadType = KeyboardType.Email
                            )
                            Spacer(modifier = modifier.height(5.dp))
                            IconInputField(
                                modifier = modifier,
                                value = viewModel.password.observeAsState().value.toString(),
                                onValueChange = { viewModel.updatePassword(it) },
                                icon = R.drawable.password_icon,
                                contentDescription = "Password",
                                isPasswordField = true,
                                hintText = "Password",
                                keypadType = KeyboardType.Password
                            )
                            Spacer(modifier = modifier.height(5.dp))
                            IconInputField(
                                modifier = modifier,
                                value = viewModel.companyName.observeAsState().value.toString(),
                                onValueChange = { viewModel.updateCompanyName(it) },
                                icon = R.drawable.user_icon,
                                contentDescription = "Company Name",
                                isPasswordField = false,
                                hintText = "Company Name",
                                keypadType = KeyboardType.Text
                            )
                            Spacer(modifier = modifier.height(5.dp))
                            IconInputField(
                                modifier = modifier,
                                value = viewModel.contactPerson.observeAsState().value.toString(),
                                onValueChange = { viewModel.updateContactPerson(it) },
                                icon = R.drawable.add_user_icon,
                                contentDescription = "Contact Person",
                                isPasswordField = false,
                                hintText = "Contact Person",
                                keypadType = KeyboardType.Text
                            )
                            Spacer(modifier = modifier.height(5.dp))
                            IconInputField(
                                modifier = modifier,
                                value = viewModel.countryName.observeAsState().value.toString(),
                                onValueChange = { viewModel.updateCountryName(it) },
                                icon = R.drawable.country_icon,
                                contentDescription = "Country",
                                isPasswordField = false,
                                hintText = "Country",
                                keypadType = KeyboardType.Text
                            )
                            Spacer(modifier = modifier.height(5.dp))
                            IconInputField(
                                modifier = modifier,
                                value = viewModel.mobileNumber.observeAsState().value.toString(),
                                onValueChange = { viewModel.updateMobileNumber(it) },
                                icon = R.drawable.phone_icon,
                                contentDescription = "Mobile Number",
                                isPasswordField = false,
                                hintText = "Mobile Number",
                                keypadType = KeyboardType.Phone
                            )
                            Spacer(modifier = modifier.height(16.dp))
                            Row(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, end = 10.dp)
                            ) {
                                PrimaryButton(modifier = modifier, buttonText = "Submit") {
                                    viewModel.submitDate()
                                }
                            }

                        })
                    Spacer(modifier.height(40.dp))
                    CopyrightFooter()
                }
            }
        }

        else -> {

        }
    }


}

@Preview
@Composable
private fun ShowRegistrationScreen() {
    //RegistrationScreen()

}