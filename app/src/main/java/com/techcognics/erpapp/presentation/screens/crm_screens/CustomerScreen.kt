package com.techcognics.erpapp.presentation.screens.crm_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.techcognics.erpapp.data.table_data.TableRowData
import com.techcognics.erpapp.presentation.base.Result
import com.techcognics.erpapp.presentation.component.Loader
import com.techcognics.erpapp.presentation.component.ScreenTitle
import com.techcognics.erpapp.presentation.component.table.DataTable
import com.techcognics.erpapp.util.Constant

@Composable
fun CustomerScreen(
    modifier: Modifier = Modifier,
    homeNavHostController: NavHostController,
    paddingValue: PaddingValues
) {

    val viewModel: CustomerViewModel = hiltViewModel()
    val scrollState = rememberScrollState()

    LaunchedEffect(true) {
        viewModel.fetchBusinessPartner()
    }

    Scaffold(modifier = modifier.padding(paddingValue), content = { it ->


        when (viewModel.BPLUIState.observeAsState().value) {
            is Result.Loading -> Loader()


            is Result.Idle, is Result.Success -> Column(
                modifier = modifier
                    .padding(it)
                    .fillMaxHeight()
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(Color.Gray)
                        .padding(start = 5.dp, end = 5.dp)
                        .height(50.dp)

                ) {

                }
                Spacer(modifier = modifier.height(5.dp))
                DataTable(
                    headers = listOf(
                        "Code",
                        "Company Name",
                        "Contact Person",
                        "Mobile Number",
                        "Address"
                    ),
                    rows = viewModel.tableRowData.observeAsState().value.orEmpty(),
                    headerColor = Color.LightGray
                )
            }

            is Result.Error -> {}
            else -> {}

        }


    }, topBar = { ScreenTitle(titleFirst = "Customer") }, floatingActionButton = {
        FloatingActionButton(onClick = {
            // Handle FAB click
            homeNavHostController.navigate(Constant.CUSTOMER_ADD_SCREEN)
        }) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    })


}