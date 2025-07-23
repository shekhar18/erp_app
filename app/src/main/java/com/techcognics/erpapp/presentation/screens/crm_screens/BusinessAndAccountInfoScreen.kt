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
import com.techcognics.erpapp.presentation.component.dropdownmenu.SimpleMultiSelectDropDown
import com.techcognics.erpapp.presentation.component.inputfields.InputField

@Composable
fun BusinessAndAccountInfoScreen(modifier: Modifier = Modifier) {
    val viewModel: CustomerViewModel = hiltViewModel()
    val scrollState = rememberScrollState()

    LaunchedEffect(true) {
        viewModel.fetchBusinessAndAccountInfo()
    }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(enabled = true, state = scrollState)
            .imePadding()
    ) {
        when (viewModel.BAIiState.observeAsState().value) {
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
                        ScreenTitle(titleFirst = "Business & Account Information")
                    }
                    Spacer(modifier = modifier.height(10.dp))

                    SimpleMultiSelectDropDown(
                        label = "T & C*",
                        optionList = viewModel.termAndCondition(),
                        selectedOption = viewModel.selectedTermAndCondition.observeAsState().value.orEmpty(),
                        onOptionSelected = {
                            viewModel.updateTermAndConditionList(it)
                        })
                    Spacer(modifier = modifier.height(10.dp))
                    InputField(
                        label = "Credit Days*",
                        value = viewModel.customerCode.observeAsState().value.orEmpty(),
                        onValueChange = { viewModel.updateFieldValue("creditDay", it) })
                    Spacer(modifier = modifier.height(10.dp))
                    SimpleDropDown(
                        label = "Billing Currency*",
                        optionList = viewModel.billingCurrencyList(),
                        selectedOption = viewModel.selectedBillingCurrency.observeAsState().value.orEmpty(),
                        onOptionSelected = {
                            viewModel.updateFieldValue("billingCurrency", it)
                        })
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