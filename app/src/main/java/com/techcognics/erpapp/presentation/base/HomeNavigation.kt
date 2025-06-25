package com.techcognics.erpapp.presentation.base

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.techcognics.erpapp.presentation.screens.DashboardScreen
import com.techcognics.erpapp.presentation.screens.SalesDashboard
import com.techcognics.erpapp.util.Constant

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ErpHomeNavHost(
    modifier: Modifier = Modifier,
    homeNavController: NavHostController,
    paddingValues: PaddingValues
) {
    val homeNavController = homeNavController
    homeNavController.currentBackStackEntryAsState()
    HomeHost(homeNavController,paddingValues)
}


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun HomeHost(homeNavController: NavHostController, paddingValues: PaddingValues,) {

    NavHost(navController = homeNavController, startDestination = Constant.DASHBOARD_SCREEN) {
        composable(Constant.DASHBOARD_SCREEN) {
            DashboardScreen(homeNavController = homeNavController)
        }
        composable(Constant.COMPANY_DASHBOARD_SCREEN) {

        }
        composable(Constant.SALES_DASHBOARD_SCREEN) {
            SalesDashboard(paddingValue = paddingValues,)
        }
        composable(Constant.PROFILE_SCREEN) {

        }
    }

}