package com.techcognics.erpapp.presentation.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.techcognics.erpapp.presentation.component.button.PrimaryButton
import com.techcognics.erpapp.presentation.component.inputfields.InputField
import com.techcognics.erpapp.presentation.component.text.RichTextRight
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
    remember { MutableInteractionSource() }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoAndTitle(fontBlue)
        Column(modifier = Modifier.width(192.dp)) {
            InputField("User ID", userId, onUserIdChange)
            Spacer(modifier = Modifier.height(10.dp))
            InputField("Password", password, onPasswordChange, isPassword = true)
            Spacer(modifier = Modifier.height(20.dp))
            RememberMeRow(checked, onCheckedChange, robotoFont)
            Spacer(modifier = Modifier.height(10.dp))
            PrimaryButton("Login") { navController.navigate(Constant.HOME_SCREEN) }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                RichTextRight(
                    text = "Don’t have an account? ",
                    highlightedText = "Sign up",
                    color = MaterialTheme.colorScheme.onBackground,
                    highlightedTextColor = MaterialTheme.colorScheme.onSurface,
                    true,
                    MaterialTheme.typography.labelSmall,
                    onclick = { navController.navigate(Constant.REGISTRATION_SCREEN) })
            }
        }
    }
}
