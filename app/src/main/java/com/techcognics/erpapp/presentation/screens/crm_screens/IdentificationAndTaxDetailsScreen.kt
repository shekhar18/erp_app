package com.techcognics.erpapp.presentation.screens.crm_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techcognics.erpapp.presentation.component.ScreenTitle
import com.techcognics.erpapp.presentation.component.inputfields.InputField

@Composable
fun IdentificationAndTaxDetailsScreen(modifier: Modifier = Modifier) {

    val viewModel: CustomerViewModel = hiltViewModel()
    val scrollState = rememberScrollState()

    Surface(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(enabled = true, state = scrollState)
            .imePadding()
    ) {
        Column {
            Row(
                modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                ScreenTitle(titleFirst = "Identification and Tax Details")
            }
            InputField(
                label = "Pan Card*",
                value = viewModel.panNumber.observeAsState().value.orEmpty(),
                onValueChange = { viewModel.updateFieldValue("panCard", it) })
            Spacer(modifier = modifier.height(10.dp))
            InputField(
                label = "GSTIN Number*",
                value = viewModel.gstNumber.observeAsState().value.orEmpty(),
                onValueChange = { viewModel.updateFieldValue("gstNumber", it) })
            Spacer(modifier = modifier.height(10.dp))
            InputField(
                label = "Adhar Card Number*",
                value = viewModel.adharNumber.observeAsState().value.orEmpty(),
                onValueChange = { viewModel.updateFieldValue("adharNumber", it) })
            Spacer(modifier = modifier.height(10.dp))
        }
    }
}