package com.techcognics.erpapp.presentation.base

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.techcognics.erpapp.presentation.screens.home.HomeScreen
import com.techcognics.erpapp.presentation.screens.login.LoginScreen
import com.techcognics.erpapp.presentation.screens.registration.RegistrationScreen
import com.techcognics.erpapp.util.Constant


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ErpAppNavHost(modifier: Modifier = Modifier, isLogin: Boolean ) {
    val navController = rememberNavController()
    navController.currentBackStackEntryAsState()
    Log.d("COMPOSE",isLogin.toString())
    Host(navController, isLogin)

}


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Host(navController: NavHostController, isLogin: Boolean) {
    NavHost(
        navController = navController,
        startDestination = if (isLogin) Constant.HOME_SCREEN else Constant.LOGIN_SCREEN
    ) {
        composable(Constant.LOGIN_SCREEN) {
            LoginScreen(mainNavController = navController)
        }
        composable(Constant.REGISTRATION_SCREEN) {
            RegistrationScreen(mainNavController = navController)
        }
        composable(Constant.HOME_SCREEN) {
            HomeScreen(mainNavController = navController)
        }
    }
}