package com.techcognics.erpapp.presentation.component

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.techcognics.erpapp.util.Constant

@Composable
fun LoginCardContent(
    userId: String,
    onUserIdChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    robotoFont: FontFamily,
    fontBlue: Color,
    navController: NavHostController
) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoAndTitle(fontBlue, robotoFont)
        Column(modifier = Modifier.width(192.dp)) {
            InputField("User ID", userId, onUserIdChange)
            Spacer(modifier = Modifier.height(10.dp))
            InputField("Password", password, onPasswordChange, isPassword = true)
            Spacer(modifier = Modifier.height(20.dp))
            RememberMeRow(checked, onCheckedChange, robotoFont)
            Spacer(modifier = Modifier.height(10.dp))
            LoginButton { navController.navigate(Constant.HOME_SCREEN) }
            Spacer(modifier = Modifier.height(8.dp))
            SignUpPrompt {
                navController.navigate(Constant.REGISTRATION_SCREEN)
                //Toast.makeText(context,"Go TO Registration", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
