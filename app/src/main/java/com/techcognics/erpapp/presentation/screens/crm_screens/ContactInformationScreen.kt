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
fun ContactInformationScreen(modifier: Modifier = Modifier) {
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
                ScreenTitle(titleFirst = "Contact Information")
            }
            InputField(
                label = "Contact Person*",
                value = viewModel.contactPerson.observeAsState().value.orEmpty(),
                onValueChange = { viewModel.updateFieldValue("contactPerson", it) })
            Spacer(modifier = modifier.height(10.dp))
            InputField(
                label = "Mobile Number*",
                value = viewModel.mobileNumber.observeAsState().value.orEmpty(),
                onValueChange = { viewModel.updateFieldValue("mobileNumber", it) })
            Spacer(modifier = modifier.height(10.dp))
            InputField(
                label = "Contact Person*",
                value = viewModel.emailId.observeAsState().value.orEmpty(),
                onValueChange = { viewModel.updateFieldValue("emailId", it) })
            Spacer(modifier = modifier.height(10.dp))
        }
    }


}