package com.techcognics.erpapp.presentation.component.bottombar

import android.widget.Toast
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.techcognics.erpapp.R
import com.techcognics.erpapp.data.BottomNavItem

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier, navController: NavHostController) {
    val context = LocalContext.current
    val bottomNavItems = listOf(
        BottomNavItem("Home", R.drawable.home_icon, "home"),
        BottomNavItem("Message", R.drawable.message_icon, "message"),
        BottomNavItem("Call", R.drawable.call_icon, "call"),
        BottomNavItem("Lock", R.drawable.lock_icon, "lock")
    )

    NavigationBar(
        modifier = modifier.windowInsetsPadding(WindowInsets.navigationBars).height(45.dp),
        tonalElevation = 10.dp,
        containerColor = Color(0xFFF8F8F8),
        content = {
            val currentDestination =
                navController.currentBackStackEntryAsState().value?.destination?.route
            bottomNavItems.forEach { item ->
                NavigationBarItem(selected = currentDestination == item.route, onClick = {
                    Toast.makeText(context, item.label, Toast.LENGTH_SHORT).show()/* navController.navigate(item.route) {
                    launchSingleTop = true
                    restoreState = true
                }*/
                }, icon = {
                    Icon(
                        modifier = modifier
                            .height(24.dp)
                            .width(24.dp),
                        painter = painterResource(item.icon),
                        contentDescription = item.label,
                        tint = Color(0xFF3B3284) // your purple color
                    )
                })
            }
        })
}