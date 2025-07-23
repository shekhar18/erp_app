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
import com.techcognics.erpapp.presentation.screens.company_dashboard.CompanyDashboardScreen
import com.techcognics.erpapp.presentation.screens.crm_screens.CustomerAddScreen
import com.techcognics.erpapp.presentation.screens.crm_screens.CustomerScreen
import com.techcognics.erpapp.presentation.screens.dashboard.DashboardScreen
import com.techcognics.erpapp.presentation.screens.sales_dashboard.SalesDashboard
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
            CompanyDashboardScreen(paddingValue = paddingValues)
        }
        composable(Constant.SALES_DASHBOARD_SCREEN) {
            SalesDashboard(paddingValue = paddingValues,)
        }
        composable(Constant.PROFILE_SCREEN) {

        }

        composable(Constant.CUSTOMER_SCREEN) {
            CustomerScreen(homeNavHostController = homeNavController,paddingValue = paddingValues,)
        }
        composable(Constant.CUSTOMER_ADD_SCREEN) {
            CustomerAddScreen(homeNavHostController = homeNavController,paddingValue = paddingValues,)
        }
    }

}