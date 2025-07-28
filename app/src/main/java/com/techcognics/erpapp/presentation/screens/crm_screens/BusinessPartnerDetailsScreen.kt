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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techcognics.erpapp.presentation.base.Result
import com.techcognics.erpapp.presentation.component.ErrorDialog
import com.techcognics.erpapp.presentation.component.Loader
import com.techcognics.erpapp.presentation.component.ScreenTitle
import com.techcognics.erpapp.presentation.component.dropdownmenu.SimpleDropDown
import com.techcognics.erpapp.presentation.component.inputfields.InputField
import com.techcognics.erpapp.presentation.component.inputfields.MultilineInputField

@Composable
fun BusinessPartnerDetailsScree(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    val viewModel: CustomerViewModel = hiltViewModel()
    var leadSource by rememberSaveable { mutableStateOf(emptyList<String>()) }

    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }

    leadSource = viewModel.leadSourceList.observeAsState().value?.map { return@map it.leadSource }
        ?: emptyList<String>()

    Surface(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(enabled = true, state = scrollState)
            .imePadding()
    ) {
        when (viewModel.BPDUiState.observeAsState().value) {
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
                        ScreenTitle(titleFirst = "Business Partner Details")
                    }
                    Spacer(modifier = modifier.height(10.dp))

                    InputField(
                        label = "Code*",
                        value = viewModel.customerCode.observeAsState().value.orEmpty(),
                        onValueChange = {
                            viewModel.updateFieldValue("code", it)
                            viewModel.updateFieldValidation("code", false)
                        },
                        validation = viewModel.customerCodeValid.observeAsState().value == true
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    InputField(
                        label = "Company Name*",
                        value = viewModel.companyName.observeAsState().value.orEmpty(),
                        onValueChange = {
                            viewModel.updateFieldValue("companyName", it)
                            viewModel.updateFieldValidation("companyName", false)
                        },
                        validation = viewModel.companyNameValid.observeAsState().value == true
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    InputField(
                        label = "Customer Pin Code*",
                        value = viewModel.customerPinCode.observeAsState().value.orEmpty(),
                        onValueChange = {
                            viewModel.updateFieldValue("customerPinCode", it)
                            viewModel.updateFieldValidation("customerPinCode", false)
                        },
                        validation = viewModel.customerPinCodeValid.observeAsState().value == true
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    InputField(
                        label = "Category",
                        value = viewModel.customerCategory.observeAsState().value.orEmpty(),
                        onValueChange = {
                            viewModel.updateFieldValue("customerCategory", it)
                            viewModel.updateFieldValidation("customerCategory", false)
                        },
                        validation = viewModel.customerCategoryValid.observeAsState().value == true
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    SimpleDropDown(
                        label = "Country*",
                        optionList = viewModel.countryList(),
                        selectedOption = viewModel.country.observeAsState().value.orEmpty(),
                        onOptionSelected = {
                            viewModel.updateFieldValue("country", it)
                            viewModel.updateFieldValidation("country", false)
                        })
                    Spacer(modifier = modifier.height(10.dp))
                    SimpleDropDown(
                        label = "State*",
                        optionList = viewModel.stateList(),
                        selectedOption = viewModel.state.observeAsState().value.orEmpty(),
                        onOptionSelected = {
                            viewModel.updateFieldValue("state", it)
                            viewModel.updateFieldValidation("state", false)
                        })
                    Spacer(modifier = modifier.height(10.dp))
                    SimpleDropDown(
                        label = "City*",
                        optionList = viewModel.cityList(),
                        selectedOption = viewModel.city.observeAsState().value.orEmpty(),
                        onOptionSelected = {
                            viewModel.updateFieldValue("city", it)
                            viewModel.updateFieldValidation("city", false)
                        })
                    Spacer(modifier = modifier.height(10.dp))
                    SimpleDropDown(
                        label = "Lead Source",
                        optionList = leadSource,
                        selectedOption = viewModel.leadSource.observeAsState().value.orEmpty(),
                        onOptionSelected = {
                            viewModel.updateFieldValue("leadSource", it)
                            viewModel.updateFieldValidation("leadSource", false)
                        })
                    Spacer(modifier = modifier.height(10.dp))
                    MultilineInputField(
                        modifier = modifier,
                        label = "Address",
                        value = viewModel.address.observeAsState().value.orEmpty(),
                        onValueChange = {
                            viewModel.updateFieldValue("address", it)
                            viewModel.updateFieldValidation("address", false)
                        },
                        validation = viewModel.addressValid.observeAsState().value == true
                    )
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ShowBusinessPartnerDetailsScreen() {
    BusinessPartnerDetailsScree()
}