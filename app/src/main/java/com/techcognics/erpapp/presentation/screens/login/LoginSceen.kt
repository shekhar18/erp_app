package com.techcognics.erpapp.presentation.screens.login

import android.content.res.Configuration
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.techcognics.erpapp.R
import com.techcognics.erpapp.presentation.base.Result
import com.techcognics.erpapp.presentation.component.CopyrightFooter
import com.techcognics.erpapp.presentation.component.Loader
import com.techcognics.erpapp.presentation.component.LoginCardContent
import com.techcognics.erpapp.util.Constant

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun LoginScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    LocalContext.current
    val viewModel: LoginViewModel = hiltViewModel()
    val loginState = viewModel.loginState.observeAsState().value


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
                        color = MaterialTheme.colorScheme.outline,
                        shape = RectangleShape
                    ), colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ), content = {

                    when (loginState) {
                        is Result.Loading -> {
                            Loader()
                        }

                        is Result.Success<*> -> {
                            Loader()
                            navController.popBackStack()
                            navController.navigate(Constant.HOME_SCREEN)
                        }

                        is Result.Error -> {
                            Text(text = loginState.message)
                        }

                        else -> {
                            LoginCardContent(
                                userId = viewModel.userId.observeAsState().value ?: "",
                                onUserIdChange = { viewModel.updateUserid(it) },
                                password = viewModel.userPassword.observeAsState().value ?: "",
                                onPasswordChange = { viewModel.updatePassword(it) },
                                checked = viewModel.rememberCheck.observeAsState().value == true,
                                onCheckedChange = { viewModel.updateRememberCheck(it) },
                                fontBlue = colorResource(R.color.font_blue_color),
                                navController,
                                onClick = {
                                    viewModel.getLogin()

                                })
                        }
                    }

                })
            Spacer(modifier.height(40.dp))
            CopyrightFooter()
        }
    }
}


@RequiresApi(Build.VERSION_CODES.Q)
@Preview(
    showSystemUi = false,
    showBackground = false,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_UNDEFINED,
    device = "spec:width=1080px,height=2340px,dpi=440"
)
@Composable
private fun ShowLoginScreenDark() {
    // LoginScreen()
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
private fun ShowLoginScreenLight() {
    // LoginScreen()
}