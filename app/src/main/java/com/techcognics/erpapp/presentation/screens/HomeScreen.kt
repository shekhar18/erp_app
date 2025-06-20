package com.techcognics.erpapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.techcognics.erpapp.R
import com.techcognics.erpapp.data.StatCard
import com.techcognics.erpapp.presentation.component.topbar.AppBar
import com.techcognics.erpapp.presentation.component.AppDrawer
import com.techcognics.erpapp.presentation.component.bottombar.BottomNavigationBar
import com.techcognics.erpapp.presentation.component.SalesComparisonCard
import com.techcognics.erpapp.presentation.component.StatCardItem
import kotlinx.coroutines.launch


@SuppressLint("ResourceType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    val stats = listOf(
        StatCard(
            "Revenue", "₹50", "Cr", Color(0xFFFFCDD2), Color(0xFFFFEBEE), R.drawable.rad_chart
        ), StatCard(
            "Expected Revenue",
            "₹13",
            "Lakhs",
            Color(0xFFC8E6C9),
            Color(0xFFE8F5E9),
            R.drawable.green_chart
        ), StatCard(
            "Sales Orders",
            "₹12",
            "Lakhs",
            Color(0xFFD1C4E9),
            Color(0xFFEDE7F6),
            R.drawable.purple_chart
        ), StatCard(
            "All Values",
            "₹12",
            "Lakhs",
            Color(0xFFFFE0B2),
            Color(0xFFFFF3E0),
            R.drawable.yellow_chart
        )
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    ModalNavigationDrawer(
        drawerState = drawerState, drawerContent = {
            AppDrawer(
                modifier,
                onClickClose = { scope.launch { if (drawerState.isOpen == true) drawerState.close() else drawerState.open() } },
                onClickMenu = {},
                onClickSignOut = {

                    navController.popBackStack(
                        destinationId = navController.graph.startDestinationId, inclusive = false
                    )

                })
        }) {
        Scaffold(
            topBar = {
                AppBar(
                    navController = navController, scope = scope, drawerState = drawerState
                )
            },
            bottomBar = { BottomNavigationBar(navController = navController) },

            modifier = modifier.background(MaterialTheme.colorScheme.background),
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Techcognics India Pvt LTD Sales Dashboard",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto)),
                            fontWeight = FontWeight.SemiBold,

                            ),
                        modifier = Modifier.padding(8.dp),
                    )
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(stats.size) { index ->
                        StatCardItem(
                            card = stats[index],
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1.6f) // adjust ratio to fit nicely
                        )
                    }
                }
                SalesComparisonCard()
            }
        }
    }


}