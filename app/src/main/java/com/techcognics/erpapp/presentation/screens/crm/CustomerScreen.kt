package com.techcognics.erpapp.presentation.screens.crm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.techcognics.erpapp.presentation.component.CustomerSearchBar
import com.techcognics.erpapp.presentation.component.table.CustomerTableHeader
import com.techcognics.erpapp.presentation.components.table.CustomerTableRow
import com.techcognics.erpapp.presentation.viewmodel.CustomerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerScreen(mainNavController: NavHostController) {
    val viewModel: CustomerViewModel = hiltViewModel()
    val customers by viewModel.customers
    var showDialog by remember { mutableStateOf(false) }

    // Form states
    var companyName by remember { mutableStateOf("") }
    var contactPerson by remember { mutableStateOf("") }
    var mobileNo by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var pinCode by remember { mutableStateOf("") }
    var cityName by remember { mutableStateOf("") }

    Scaffold(
        //CustomerTopBar()
        topBar = { TopAppBar(title = { Text("Customer List") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Customer")
            }
        }
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            CustomerSearchBar(onSearch = { /* Implement search later */ })
            Spacer(Modifier.height(8.dp))
            CustomerTableHeader()
            LazyColumn {
                items(customers) { customer ->
                    CustomerTableRow(
                        customer = customer,
                        onEdit = { /* Edit logic */ },
                        onDelete = { /* Delete logic */ },
                        onView = { /* View details logic */ }
                    )
                }
            }
        }
    }

    // ✅ Add Customer Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Add Customer") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = companyName, onValueChange = { companyName = it }, label = { Text("Company Name") })
                    OutlinedTextField(value = contactPerson, onValueChange = { contactPerson = it }, label = { Text("Contact Person") })
                    OutlinedTextField(value = mobileNo, onValueChange = { mobileNo = it }, label = { Text("Mobile No") })
                    OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
                    OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Address") })
                    OutlinedTextField(value = pinCode, onValueChange = { pinCode = it }, label = { Text("Pin Code") })
                    OutlinedTextField(value = cityName, onValueChange = { cityName = it }, label = { Text("City Name") })
                }
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.addCustomer(companyName, contactPerson, mobileNo, email, address, pinCode, cityName)
                    showDialog = false
                    companyName = ""; contactPerson = ""; mobileNo = ""; email = ""; address = ""; pinCode = ""; cityName = ""
                }) { Text("Save") }
            },
            dismissButton = {
                OutlinedButton(onClick = { showDialog = false }) { Text("Cancel") }
            }
        )
    }
}
//
//@Preview(showBackground = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_DESK,
//    wallpaper = Wallpapers.NONE, showSystemUi = true
//)
//@Composable
//private fun CustomerScreenPreview() {
//    CustomerScreen()
//
//}