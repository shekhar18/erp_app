package com.techcognics.erpapp.presentation.screens.profile



import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techcognics.erpapp.presentation.screens.profile.ProfileViewModel

@Composable
fun Profile(viewModel: ProfileViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }
            state.error.isNotEmpty() -> {
                Text(text = "Error: ${state.error}", color = MaterialTheme.colorScheme.error)
            }
            else -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    val profile = state.profileList.firstOrNull()
                    if (profile != null) {
                        Text(text = "First Name: ${profile.firstName}")
                        Text(text = "Last Name: ${profile.lastName}")
                        Text(text = "Email: ${profile.email}")
                    } else {
                        Text(text = "No profile data available.")
                    }
                }
            }
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
private fun ShowProfileScreenDark() {
    ProfileScreen()// LoginScreen()
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview(device = "spec:width=1080px,height=2340px,dpi=440,isRound=true",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    wallpaper = Wallpapers.NONE
)
@Composable
private fun ShowProfileScreenLight() {
    ProfileScreen()// LoginScreen()

}