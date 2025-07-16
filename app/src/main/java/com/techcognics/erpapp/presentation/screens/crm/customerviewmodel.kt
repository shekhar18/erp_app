package com.techcognics.erpapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcognics.erpapp.data.model.City
import com.techcognics.erpapp.data.model.Country
import com.techcognics.erpapp.data.model.Customer
import com.techcognics.erpapp.domain.usecase.AddCustomerUseCase
import com.techcognics.erpapp.domain.usecase.GetCustomersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val getCustomersUseCase: GetCustomersUseCase, // ✅ Use correct naming
    private val addCustomerUseCase: AddCustomerUseCase
) : ViewModel() {

    // ✅ State for UI
    private val _customers = mutableStateOf<List<Customer>>(emptyList())
    val customers: State<List<Customer>> = _customers

    init {
        loadCustomers() // ✅ Load customers on ViewModel init
    }

    // ✅ Fetch customers from API
    fun loadCustomers() {
        viewModelScope.launch {
            try {
                _customers.value = getCustomersUseCase() // ✅ Call use case
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // ✅ Add new customer and refresh list
    fun addCustomer(
        companyName: String,
        contactPerson: String,
        mobileNo: String,
        email: String,
        address: String,
        pinCode: String,
        cityName: String
    ) {
        viewModelScope.launch {
            try {
                val newCustomer = Customer(
                    bpCompanyName = companyName,
                    contactPerson = contactPerson,
                    mobileNo = mobileNo,
                    email = email,
                    address = address,
             //       pinCode = pinCode,
                    city = City(name = cityName),
                    country = Country(code = "IN", name = "India")
                )
                addCustomerUseCase(newCustomer) // ✅ Call API to add
                loadCustomers() // ✅ Refresh after adding
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}