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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techcognics.erpapp.presentation.component.ScreenTitle
import com.techcognics.erpapp.presentation.component.dropdownmenu.SimpleDropDown
import com.techcognics.erpapp.presentation.component.inputfields.InputField
import com.techcognics.erpapp.presentation.component.inputfields.MultilineInputField

@Composable
fun BusinessShipAddress(modifier: Modifier = Modifier) {
    val viewModel: CustomerViewModel = hiltViewModel()
    val scrollState = rememberScrollState()
    Surface(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(enabled = true, state = scrollState)
            .imePadding()
    ) {


        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                ScreenTitle(titleFirst = "Business Ship Address")
            }
            Spacer(modifier = modifier.height(10.dp))

            InputField(
                label = "Ship Name*",
                value = viewModel.customerCode.observeAsState().value.orEmpty(),
                onValueChange = { viewModel.updateFieldValue("shipName", it) })
            Spacer(modifier = modifier.height(10.dp))
            SimpleDropDown(
                label = "Country*",
                optionList = viewModel.countryList(),
                selectedOption = viewModel.country.observeAsState().value.orEmpty(),
                onOptionSelected = {
                    viewModel.updateFieldValue("country", it)
                })
            Spacer(modifier = modifier.height(10.dp))
            SimpleDropDown(
                label = "State*",
                optionList = viewModel.stateList(),
                selectedOption = viewModel.state.observeAsState().value.orEmpty(),
                onOptionSelected = {
                    viewModel.updateFieldValue("state", it)
                })
            Spacer(modifier = modifier.height(10.dp))
            SimpleDropDown(
                label = "City*",
                optionList = viewModel.cityList(),
                selectedOption = viewModel.city.observeAsState().value.orEmpty(),
                onOptionSelected = {
                    viewModel.updateFieldValue("city", it)
                })
            InputField(
                label = "Pin Code*",
                value = viewModel.customerCode.observeAsState().value.orEmpty(),
                onValueChange = { viewModel.updateFieldValue("code", it) })
            Spacer(modifier = modifier.height(10.dp))
            MultilineInputField(
                modifier = modifier,
                label = "Ship Address*",
                value = viewModel.address.observeAsState().value.orEmpty(),
                onValueChange = {
                    viewModel.updateFieldValue("address", it)
                })


        }

    }
}