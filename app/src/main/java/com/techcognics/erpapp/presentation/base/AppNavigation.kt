package com.techcognics.erpapp.presentation.base

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.techcognics.erpapp.presentation.screens.HomeScreen
import com.techcognics.erpapp.presentation.screens.LoginScreen
import com.techcognics.erpapp.presentation.screens.RegistrationScreen
import com.techcognics.erpapp.util.Constant


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ErpAppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    navController.currentBackStackEntryAsState()

    Host(navController)


}


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Host(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Constant.LOGIN_SCREEN) {
        composable(Constant.LOGIN_SCREEN) {
            LoginScreen(navController = navController)
        }
        composable(Constant.REGISTRATION_SCREEN) {
            RegistrationScreen(navController = navController)
        }
        composable(Constant.HOME_SCREEN) {
            HomeScreen(navController = navController)
        }
    }
}