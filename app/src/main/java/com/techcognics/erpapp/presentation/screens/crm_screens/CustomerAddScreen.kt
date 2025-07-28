package com.techcognics.erpapp.presentation.screens.crm_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.techcognics.erpapp.presentation.component.MultiStepFormPager
import com.techcognics.erpapp.presentation.component.ScreenTitle

@Composable
fun CustomerAddScreen(
    modifier: Modifier = Modifier,
    homeNavHostController: NavHostController,
    paddingValue: PaddingValues
) {
    val viewModel: CustomerViewModel = hiltViewModel()




    Column(modifier = modifier.padding(paddingValue)) {
        Row(
            modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start
        ) {
            ScreenTitle(titleFirst = "Create Business Partner Master")
        }
        MultiStepFormPager(
            homeNavHostController = homeNavHostController, pages = listOf<@Composable () -> Unit>(
                @Composable { BusinessPartnerDetailsScree() },
                @Composable { ContactInformationScreen() },
                @Composable { BusinessShipAddress() },
                @Composable { IdentificationAndTaxDetailsScreen() },
                @Composable { BusinessAndAccountInfoScreen() },
                @Composable { PaymentAndFinancialsScreen() },
            ), onSubmit = {

                // viewModel.submitForm()
                homeNavHostController.popBackStack()
            }) {
            viewModel.validationForCreateCustomer(it)
        }


    }

}


