package com.techcognics.erpapp.presentation.screens.crm_screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.techcognics.erpapp.presentation.component.ScreenTitle
import com.techcognics.erpapp.presentation.component.inputfields.InputFieldWithIcon
import com.techcognics.erpapp.util.Constant

@Composable
fun CustomerScreen(
    modifier: Modifier = Modifier,
    homeNavHostController: NavHostController,
    paddingValue: PaddingValues
) {

    val customerViewModel: CustomerViewModel = hiltViewModel()


    Scaffold(modifier = modifier.padding(paddingValue),
        content = {it ->
            LazyColumn(modifier = modifier.padding(it)) {
                item {
                    Row(modifier = modifier.padding(start = 10.dp, end = 10.dp).height(58.dp)) {
                        InputFieldWithIcon(
                            value = customerViewModel.customerSearch.observeAsState().value.orEmpty(),
                            onValueChange = { customerViewModel.updateCustomerSearch(it) },
                            label = "Customer Search",
                            hint = "Search",
                            modifier = Modifier

                        )
                    }

                }
            }
        },
        topBar = { ScreenTitle(titleFirst = "Customer")},
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Handle FAB click
                homeNavHostController.navigate(Constant.CUSTOMER_ADD_SCREEN)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    )




}