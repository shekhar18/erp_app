package com.techcognics.erpapp.presentation.screens.home

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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.techcognics.erpapp.presentation.base.ErpHomeNavHost
import com.techcognics.erpapp.presentation.component.AppDrawer
import com.techcognics.erpapp.presentation.component.bottombar.BottomNavigationBar
import com.techcognics.erpapp.presentation.component.topbar.AppBar
import com.techcognics.erpapp.util.Constant
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("ResourceType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, mainNavController: NavHostController) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val homeNavController = rememberNavController()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()




    ModalNavigationDrawer(
        drawerState = drawerState, drawerContent = {
            AppDrawer(
                modifier,
                menuList = homeViewModel.drawerMenuList.observeAsState().value,
                mainNavController = mainNavController,
                homeNavController = homeNavController,
                onClickClose = { scope.launch { if (drawerState.isOpen == true) drawerState.close() else drawerState.open() } },
                onClickMenu = {},
                onClickSignOut = {
                    mainNavController.navigate(Constant.LOGIN_SCREEN)
                })
        }) {
        Scaffold(
            topBar = {
                AppBar(
                    mainNavController = mainNavController,
                    scope = scope,
                    drawerState = drawerState,
                    homeNavController = homeNavController,
                    onCLickSignOut = {
                        homeViewModel.getSignOut()
                    }
                )
            },
            bottomBar = {
                BottomNavigationBar(
                    mainNavController = mainNavController,
                    homeNavController = homeNavController
                )
            },

            modifier = modifier.background(MaterialTheme.colorScheme.background),
        ) {

            ErpHomeNavHost(
                modifier = modifier.padding(it),
                homeNavController = homeNavController,
                paddingValues = it
            )

            //SalesDashboard(modifier = modifier, paddingValue = it, state = salesState)

        }
    }


}