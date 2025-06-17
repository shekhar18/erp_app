package com.techcognics.erpapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.techcognics.erpapp.R
import com.techcognics.erpapp.util.Constant

@Composable
fun AppDrawer(
    modifier: Modifier = Modifier, onClickMenu: () -> Unit, onClickClose: () -> Unit
) {
    val stagesOne = listOf(
        "Company Setup", "User Management", "Role Management", "Authorization To Role"
    )
    val stagesTwo = listOf(
        "City Master",
        "State Master",
        "Country Master",
        "HSN & GST Master",
        "Currency Master",
        "Unit Of Measurement",
        "Financial Year"
    )

    Box(
        modifier = Modifier
            .width(LocalConfiguration.current.screenWidthDp.dp * 0.75f)
            .fillMaxHeight()
            .background(Color.White) // optional background
    ) {
        Column {
            Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                IconButton(onClick = onClickClose) {
                    Icon(
                        modifier = modifier
                            .height(27.dp)
                            .width(27.dp),
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Close"
                    )
                }


            }



            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    DrawerTitle(
                        text = "DASHBOARD",
                        icon = R.drawable.dashboard_icon,  // replace with your icon
                        isSelected = true
                    )
                    MenuItem(
                        modifier = modifier,
                        icon = R.drawable.admin_icon,
                        menuTitle = "ADMIN UTILITY"
                    )
                    VerticalStageStepper(stages = stagesOne)
                    Spacer(modifier = modifier.height(10.dp))
                    MenuItem(
                        modifier = modifier,
                        icon = R.drawable.configration_icon,
                        menuTitle = "CONFIGURATION"
                    )
                    VerticalStageStepper(stages = stagesTwo)
                    Row(modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp)) {
                        Spacer(
                            modifier = modifier
                                .height(0.5.dp)
                                .width(LocalConfiguration.current.screenWidthDp.dp * 0.35f)
                                .background(Color.Gray)
                                .padding(start = 30.dp)
                        )
                    }
                }
                items(Constant.menuItems.size) { item ->
                    MenuItem(
                        modifier = modifier,
                        icon = Constant.menuItems[item].icon,
                        menuTitle = Constant.menuItems[item].title
                    )
                }
            }

        }
    }
}