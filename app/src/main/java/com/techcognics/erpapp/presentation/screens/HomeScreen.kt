package com.techcognics.erpapp.presentation.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.techcognics.erpapp.R
import com.techcognics.erpapp.data.StatCard
import com.techcognics.erpapp.presentation.base.ErpHomeNavHost
import com.techcognics.erpapp.presentation.component.AppDrawer
import com.techcognics.erpapp.presentation.component.bottombar.BottomNavigationBar
import com.techcognics.erpapp.presentation.component.topbar.AppBar
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("ResourceType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, mainNavController: NavHostController) {

    val homeNavController = rememberNavController()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    ModalNavigationDrawer(
        drawerState = drawerState, drawerContent = {
            AppDrawer(
                modifier,
                mainNavController = mainNavController,
                homeNavController = homeNavController,
                onClickClose = { scope.launch { if (drawerState.isOpen == true) drawerState.close() else drawerState.open() } },
                onClickMenu = {},
                onClickSignOut = {

                    /*mainNavController.popBackStack(
                        destinationId = mainNavController.graph.startDestinationId, inclusive = false
                    )*/

                })
        }) {
        Scaffold(
            topBar = {
                AppBar(
                    navController = mainNavController, scope = scope, drawerState = drawerState
                )
            },
            bottomBar = { BottomNavigationBar(navController = mainNavController) },

            modifier = modifier.background(MaterialTheme.colorScheme.background),
        ) {

            ErpHomeNavHost(modifier = modifier.padding(it),homeNavController = homeNavController,paddingValues = it)

            //SalesDashboard(modifier = modifier, paddingValue = it, state = salesState)

        }
    }


}