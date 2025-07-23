package com.techcognics.erpapp.presentation.screens.crm_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techcognics.erpapp.presentation.base.Result
import com.techcognics.erpapp.presentation.component.ErrorDialog
import com.techcognics.erpapp.presentation.component.Loader
import com.techcognics.erpapp.presentation.component.ScreenTitle
import com.techcognics.erpapp.presentation.component.dropdownmenu.SimpleDropDown
import com.techcognics.erpapp.presentation.component.inputfields.InputField

@Composable
fun PaymentAndFinancialsScreen(modifier: Modifier = Modifier) {
    val viewModel: CustomerViewModel = hiltViewModel()
    val scrollState = rememberScrollState()

    LaunchedEffect(true) {
        viewModel.fetchPaymentAndFinancials()
    }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(enabled = true, state = scrollState)
            .imePadding()
    ) {
        when (viewModel.PFiState.observeAsState().value) {
            is Result.Loading -> Loader()
            is Result.Idle, is Result.Success -> {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ScreenTitle(titleFirst = "Payment & Financials")
                    }
                    Spacer(modifier = modifier.height(10.dp))

                    SimpleDropDown(
                        label = "Customer Control Account*",
                        optionList = viewModel.controlAccountList(),
                        selectedOption = viewModel.selectionAccountControl.observeAsState().value.orEmpty(),
                        onOptionSelected = {
                            viewModel.updateFieldValue("accountControl", it)
                        })
                    Spacer(modifier = modifier.height(10.dp))


                    SimpleDropDown(
                        label = "Industry Code*",
                        optionList = viewModel.industryList(),
                        selectedOption = viewModel.selectedIndustry.observeAsState().value.orEmpty(),
                        onOptionSelected = {
                            viewModel.updateFieldValue("industry", it)
                        })
                    Spacer(modifier = modifier.height(10.dp))

                    SimpleDropDown(
                        label = "Sales Person Code*",
                        optionList = viewModel.salesPersonList(),
                        selectedOption = viewModel.selectedSalesPerson.observeAsState().value.orEmpty(),
                        onOptionSelected = {
                            viewModel.updateFieldValue("salsePerson", it)
                        })
                    Spacer(modifier = modifier.height(10.dp))

                    InputField(
                        label = "Tax Category Code*",
                        value = viewModel.taxCategoryCode.observeAsState().value.orEmpty(),
                        onValueChange = { viewModel.updateFieldValue("taxCategoryCode", it) })
                    Spacer(modifier = modifier.height(10.dp))
                    InputField(
                        label = "Group Customer Code*",
                        value = viewModel.groupCustomerCode.observeAsState().value.orEmpty(),
                        onValueChange = { viewModel.updateFieldValue("groupCustomerCode", it) })
                    Spacer(modifier = modifier.height(10.dp))


                }
            }

            is Result.Error -> {
                val message = (viewModel.BPDUiState.observeAsState().value as Result.Error).message
                ErrorDialog(
                    onClick = {
                        viewModel.fetchData()
                    }, message = message
                )
            }

            else -> null
        }

    }


}