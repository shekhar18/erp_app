package com.techcognics.erpapp.presentation.screens.crm_screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcognics.erpapp.data.CityResponse
import com.techcognics.erpapp.data.ControlAccountDetailsResponse
import com.techcognics.erpapp.data.CountryResponse
import com.techcognics.erpapp.data.CurrencyResponse
import com.techcognics.erpapp.data.IndustryResponse
import com.techcognics.erpapp.data.LeadSourceResponse
import com.techcognics.erpapp.data.PaymentTermsResponse
import com.techcognics.erpapp.data.SalesTeamResponse
import com.techcognics.erpapp.data.StateResponse
import com.techcognics.erpapp.data.crm_data.CusotmerResponce
import com.techcognics.erpapp.data.table_data.TableRowData
import com.techcognics.erpapp.domain.usecase.GetBillingCurrencyUseCase
import com.techcognics.erpapp.domain.usecase.GetBusinessPartnerUseCase
import com.techcognics.erpapp.domain.usecase.GetCityUseCase
import com.techcognics.erpapp.domain.usecase.GetCountryUseCase
import com.techcognics.erpapp.domain.usecase.GetIndustryUseCase
import com.techcognics.erpapp.domain.usecase.GetLeadSourceUseCase
import com.techcognics.erpapp.domain.usecase.GetPaymentConditionUseCase
import com.techcognics.erpapp.domain.usecase.GetSalesTeamUseCase
import com.techcognics.erpapp.domain.usecase.GetStateUseCase
import com.techcognics.erpapp.domain.usecase.GetTokenUseCase
import com.techcognics.erpapp.domain.usecase.RetrieveAccountControlUseCase
import com.techcognics.erpapp.presentation.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(
    val getCountryUseCase: GetCountryUseCase,
    val getStateUseCase: GetStateUseCase,
    val getCityUseCase: GetCityUseCase,
    val getLeadSourceUseCase: GetLeadSourceUseCase,
    val getTokenUseCase: GetTokenUseCase,
    val getBillingCurrencyUseCase: GetBillingCurrencyUseCase,
    val getPaymentConditionUseCase: GetPaymentConditionUseCase,
    val retrieveAccountControlUseCase: RetrieveAccountControlUseCase,
    val salesTeamUseCase: GetSalesTeamUseCase,
    val getIndustryUseCase: GetIndustryUseCase,
    val getBusinessPartnerUseCase: GetBusinessPartnerUseCase,
) : ViewModel() {


    private val _customerSearch: MutableLiveData<String> = MutableLiveData<String>("")
    val customerSearch: LiveData<String> = _customerSearch


    //Business Partner Details field
    private val _customerCode: MutableLiveData<String> = MutableLiveData<String>("")
    val customerCode: LiveData<String> = _customerCode
    private val _customerCodeValid: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val customerCodeValid: LiveData<Boolean> = _customerCodeValid

    //....
    private val _companyName: MutableLiveData<String> = MutableLiveData<String>("")
    val companyName: LiveData<String> = _companyName
    private val _companyNameValid: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val companyNameValid: LiveData<Boolean> = _companyNameValid

    //....
    private val _customerPinCode: MutableLiveData<String> = MutableLiveData<String>("")
    val customerPinCode: LiveData<String> = _customerPinCode
    private val _customerPinCodeValid: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val customerPinCodeValid: LiveData<Boolean> = _customerPinCodeValid

    //....
    private val _customerCategory: MutableLiveData<String> = MutableLiveData<String>("")
    val customerCategory: LiveData<String> = _customerCategory
    private val _customerCategoryValid: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val customerCategoryValid: LiveData<Boolean> = _customerCategoryValid

    //...
    private val _country: MutableLiveData<String> = MutableLiveData<String>("")
    val country: LiveData<String> = _country
    private val _countryValid: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val countryValid: LiveData<Boolean> = _countryValid


    //....
    private val _state: MutableLiveData<String> = MutableLiveData<String>("")
    val state: LiveData<String> = _state
    private val _stateValid: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val stateValid: LiveData<Boolean> = _stateValid


    //....
    private val _city: MutableLiveData<String> = MutableLiveData<String>("")
    val city: LiveData<String> = _city
    private val _cityValid: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val cityValid: LiveData<Boolean> = _cityValid


    //....
    private val _leadSource: MutableLiveData<String> = MutableLiveData<String>("")
    val leadSource: LiveData<String> = _leadSource
    private val _leadSourceValid: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val leadSourceValid: LiveData<Boolean> = _leadSourceValid


    //....
    private val _address: MutableLiveData<String> = MutableLiveData<String>("")
    val address: LiveData<String> = _address
    private val _addressValid: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val addressValid: LiveData<Boolean> = _addressValid

    //....
    private val _contactPerson: MutableLiveData<String> = MutableLiveData<String>("")
    val contactPerson: LiveData<String> = _contactPerson
    private val _contactPersonValid: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val contactPersonValid: LiveData<Boolean> = _contactPersonValid

    //...
    private val _mobileNumber: MutableLiveData<String> = MutableLiveData<String>("")
    val mobileNumber: LiveData<String> = _mobileNumber
    private val _mobileNumberValid: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val mobileNumberValid: LiveData<Boolean> = _mobileNumberValid

    //....
    private val _emailId: MutableLiveData<String> = MutableLiveData<String>("")
    val emailId: LiveData<String> = _emailId
    private val _emailIdValid: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val emailIdValid: LiveData<Boolean> = _emailIdValid

    //....
    private val _shipName: MutableLiveData<String> = MutableLiveData<String>("")
    val shipName: LiveData<String> = _shipName
    private val _shipNameValid: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val shipNameValid: LiveData<Boolean> = _shipNameValid

    //...
    private val _panNumber: MutableLiveData<String> = MutableLiveData<String>("")
    val panNumber: LiveData<String> = _panNumber
    private val _panNumberValid: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val panNumberValid: LiveData<Boolean> = _panNumberValid


    //....
    private val _gstNumber: MutableLiveData<String> = MutableLiveData<String>("")
    val gstNumber: LiveData<String> = _gstNumber
    private val _gstNumberValid: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val gstNumberValid: LiveData<Boolean> = _gstNumberValid

    //....
    private val _adharNumber: MutableLiveData<String> = MutableLiveData<String>("")
    val adharNumber: LiveData<String> = _adharNumber
    private val _adharNumberValid: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val adharNumberValid: LiveData<Boolean> = _adharNumberValid

    //....
    private val _countryList: MutableLiveData<List<CountryResponse>> =
        MutableLiveData<List<CountryResponse>>(emptyList<CountryResponse>())
    val countryList: LiveData<List<CountryResponse>> = _countryList

    private val _stateList: MutableLiveData<List<StateResponse>> =
        MutableLiveData<List<StateResponse>>(emptyList<StateResponse>())
    val stateList: LiveData<List<StateResponse>> = _stateList

    private val _cityList: MutableLiveData<List<CityResponse>> =
        MutableLiveData<List<CityResponse>>(emptyList<CityResponse>())
    val cityList: LiveData<List<CityResponse>> = _cityList

    private val _leadSourceList: MutableLiveData<List<LeadSourceResponse>> =
        MutableLiveData<List<LeadSourceResponse>>(emptyList<LeadSourceResponse>())
    val leadSourceList: LiveData<List<LeadSourceResponse>> = _leadSourceList

    private val _BPDUiState: MutableLiveData<Result<Unit>> =
        MutableLiveData<Result<Unit>>(Result.Idle)
    val BPDUiState: LiveData<Result<Unit>> = _BPDUiState
    //Business Partner Details field


    //Business And Account Information field
    private val _conditionList: MutableLiveData<List<PaymentTermsResponse>> =
        MutableLiveData<List<PaymentTermsResponse>>(emptyList<PaymentTermsResponse>())
    val conditionList: LiveData<List<PaymentTermsResponse>> = _conditionList

    private val _billingCurrency: MutableLiveData<List<CurrencyResponse>> =
        MutableLiveData<List<CurrencyResponse>>(emptyList<CurrencyResponse>())
    val billingCurrency: LiveData<List<CurrencyResponse>> = _billingCurrency

    private val _BAIiState: MutableLiveData<Result<Unit>> =
        MutableLiveData<Result<Unit>>(Result.Idle)
    val BAIiState: LiveData<Result<Unit>> = _BAIiState

    private val _creditDays: MutableLiveData<String> = MutableLiveData<String>("")
    val creditDays: LiveData<String> = _creditDays

    private val _selectedBillingCurrency: MutableLiveData<String> = MutableLiveData<String>("")
    val selectedBillingCurrency: LiveData<String> = _selectedBillingCurrency

    private val _selectedTermAndCondition: MutableLiveData<List<String>> =
        MutableLiveData<List<String>>(emptyList<String>())
    val selectedTermAndCondition: LiveData<List<String>> = _selectedTermAndCondition

    //Business And Account Information field


    //payment and financials field
    private val _PFiState: MutableLiveData<Result<Unit>> =
        MutableLiveData<Result<Unit>>(Result.Idle)
    val PFiState: LiveData<Result<Unit>> = _PFiState

    private val _salesTeme: MutableLiveData<List<SalesTeamResponse>> =
        MutableLiveData<List<SalesTeamResponse>>(emptyList<SalesTeamResponse>())
    val salesTeme: LiveData<List<SalesTeamResponse>> = _salesTeme

    private val _industry: MutableLiveData<List<IndustryResponse>> =
        MutableLiveData<List<IndustryResponse>>(emptyList<IndustryResponse>())
    val industry: LiveData<List<IndustryResponse>> = _industry

    private val _controlAccount: MutableLiveData<List<ControlAccountDetailsResponse>> =
        MutableLiveData<List<ControlAccountDetailsResponse>>(emptyList<ControlAccountDetailsResponse>())
    val controlAccount: LiveData<List<ControlAccountDetailsResponse>> = _controlAccount


    private val _selectionAccountControl: MutableLiveData<String> = MutableLiveData<String>("")
    val selectionAccountControl: LiveData<String> = _selectionAccountControl

    private val _selectedIndustry: MutableLiveData<String> = MutableLiveData<String>("")
    val selectedIndustry: LiveData<String> = _selectedIndustry
    private val _selectedSalesPerson: MutableLiveData<String> = MutableLiveData<String>("")
    val selectedSalesPerson: LiveData<String> = _selectedSalesPerson


    private val _taxCategoryCode: MutableLiveData<String> = MutableLiveData<String>("")
    val taxCategoryCode: LiveData<String> = _taxCategoryCode

    private val _groupCustomerCode: MutableLiveData<String> = MutableLiveData<String>("")
    val groupCustomerCode: LiveData<String> = _groupCustomerCode
    //payment and financials field

    //business partner
    private val _BPLUIState: MutableLiveData<Result<Unit>> = MutableLiveData<Result<Unit>>()
    val BPLUIState: LiveData<Result<Unit>> = _BPLUIState

    private val _businessPartnerList: MutableLiveData<List<CusotmerResponce>> =
        MutableLiveData<List<CusotmerResponce>>()
    val businessPartnerList: LiveData<List<CusotmerResponce>> = _businessPartnerList

    private val _tableRowData: MutableLiveData<List<TableRowData>> =
        MutableLiveData<List<TableRowData>>(
            emptyList()
        )
    val tableRowData: LiveData<List<TableRowData>> = _tableRowData
    //business partner


    fun updateCustomerSearch(value: String) {
        _customerSearch.value = value

    }

    fun updateFieldValue(field: String, value: String) {
        when (field) {
            "code" -> _customerCode.value = value
            "companyName" -> _companyName.value = value
            "customerPinCode" -> _customerPinCode.value = value
            "customerCategory" -> _customerCategory.value = value
            "country" -> _country.value = value
            "state" -> _state.value = value
            "city" -> _city.value = value
            "leadSource" -> _leadSource.value = value
            "address" -> _address.value = value
            "contactPerson" -> _contactPerson.value = value
            "mobileNumber" -> _mobileNumber.value = value
            "emailId" -> _emailId.value = value
            "shipName" -> _shipName.value = value
            "panCard" -> _panNumber.value = value
            "gstNumber" -> _gstNumber.value = value
            "adharNumber" -> _adharNumber.value = value
            "creditDay" -> _creditDays.value = value
            "billingCurrency" -> _selectedBillingCurrency.value = value
            "accountControl" -> _selectionAccountControl.value = value
            "industry" -> _selectedIndustry.value = value
            "salsePerson" -> _selectedSalesPerson.value = value
            "taxCategoryCode" -> _taxCategoryCode.value = value
            "groupCustomerCode" -> _groupCustomerCode.value = value
        }
    }

    fun updateFieldValidation(field: String, value: Boolean) {
        when (field) {
            "code" -> _customerCodeValid.value = value
            "companyName" -> _companyNameValid.value = value
            "customerPinCode" -> _customerPinCodeValid.value = value
            "customerCategory" -> _customerCategoryValid.value = value
            "country" -> _countryValid.value = value
            "state" -> _stateValid.value = value
            "city" -> _cityValid.value = value
            "leadSource" -> _leadSourceValid.value = value
            "address" -> _addressValid.value = value
            "contactPerson" -> _contactPersonValid.value = value
            "mobileNumber" -> _mobileNumberValid.value = value
            "emailId" -> _emailIdValid.value = value
            "shipName" -> _shipNameValid.value = value
            "panCard" -> _panNumberValid.value = value
            "gstNumber" -> _gstNumberValid.value = value
            "adharNumber" -> _adharNumberValid.value = value
            /*"creditDay" -> _creditDaysValid.value = value
            "billingCurrency" -> _selectedBillingCurrencyValid.value = value
            "accountControl" -> _selectionAccountControlValid.value = value
            "industry" -> _selectedIndustryValid.value = value
            "salsePerson" -> _selectedSalesPersonValid.value = value
            "taxCategoryCode" -> _taxCategoryCodeValid.value = value
            "groupCustomerCode" -> _groupCustomerCodeValid.value = value*/
        }
    }


    fun updateTermAndConditionList(list: List<String>) {
        _selectedTermAndCondition.value = list
    }

    fun fetchData() {
        _BPDUiState.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            supervisorScope {
                getTokenUseCase.invoke().collect { token ->
                    val countryList = getCountryUseCase.invoke(token.toString())
                    val stateList = getStateUseCase.invoke(token.toString())
                    val cityList = getCityUseCase.invoke(token.toString())
                    val leadSourceList = getLeadSourceUseCase.invoke(token.toString())
                    _countryList.postValue(countryList)
                    _stateList.postValue(stateList)
                    _cityList.postValue(cityList)
                    _leadSourceList.postValue(leadSourceList)
                    withContext(Dispatchers.Main) {
                        _BPDUiState.value = Result.Success(Unit)
                    }
                }
            }
        }
    }

    fun fetchBusinessAndAccountInfo() {
        _BAIiState.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            supervisorScope {
                getTokenUseCase.invoke().collect { token ->
                    val billingCurrency = getBillingCurrencyUseCase.invoke(token.toString())
                    val condition = getPaymentConditionUseCase.invoke(token.toString())

                    _conditionList.postValue(condition)
                    _billingCurrency.postValue(billingCurrency)

                    withContext(Dispatchers.Main) {
                        _BAIiState.value = Result.Success(Unit)
                    }
                }
            }
        }
    }

    fun fetchPaymentAndFinancials() {
        _PFiState.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            supervisorScope {
                getTokenUseCase.invoke().collect { token ->
                    val salesTeam = salesTeamUseCase.invoke(token.toString())
                    _salesTeme.postValue(salesTeam)
                    val accountControl = retrieveAccountControlUseCase.invoke(token.toString())
                    _controlAccount.postValue(accountControl)
                    val industry = getIndustryUseCase.invoke(token.toString())
                    _industry.postValue(industry)
                    withContext(Dispatchers.Main) {
                        _PFiState.value = Result.Success(Unit)
                    }

                }
            }

        }

    }

    fun fetchBusinessPartner() {
        _BPLUIState.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            supervisorScope {
                getTokenUseCase.invoke().collect { token ->
                    val businessPartner = getBusinessPartnerUseCase.invoke(token.toString())
                    _businessPartnerList.postValue(businessPartner)

                    val listOfTableRows = businessPartner.map {
                         TableRowData(
                            listOf<String>(
                                it.bpCode,
                                it.bpCompanyName,
                                it.contactPerson,
                                it.mobileNo,
                                it.address
                            )
                        )
                    }
                    _tableRowData.postValue(listOfTableRows)


                    withContext(Dispatchers.Main) {
                        _BPLUIState.value = Result.Success(Unit)
                    }

                }
            }
        }

    }


    fun countryList(): List<String> {
        return _countryList.value?.map { return@map it.countryName } ?: emptyList<String>()
    }

    fun stateList(): List<String> {
        val countryId = _countryList.value?.find { it.countryName == _country.value }?.id ?: 0
        return _stateList.value?.filter { it.refId == countryId }?.map { return@map it.stateName }
            ?: emptyList<String>()

    }

    fun cityList(): List<String> {
        val stateId = _stateList.value?.find { it.stateName == _state.value }?.id ?: 0
        return _cityList.value?.filter { it.refId == stateId }?.map { return@map it.cityName }
            ?: emptyList<String>()
    }

    fun billingCurrencyList(): List<String> {
        return _billingCurrency.value?.map { return@map it.name } ?: emptyList<String>()
    }

    fun termAndCondition(): List<String> {
        return _conditionList.value?.map { return@map it.termsDescription } ?: emptyList<String>()
    }


    fun industryList(): List<String> {
        return _industry.value?.map { return@map "${it.industryCode} - ${it.industryName}" }
            ?: emptyList<String>()
    }

    fun controlAccountList(): List<String> {
        return _controlAccount.value?.map { return@map "${it.customerAccountCode} - ${it.controlType}" }
            ?: emptyList<String>()
    }

    fun salesPersonList(): List<String> {
        return _salesTeme.value?.map { return@map it.name } ?: emptyList<String>()
    }


    fun validationForCreateCustomer(pageNumber: Int) {

        when (pageNumber) {
            1 -> getValidationOfBusinessPartnerDetails()
            2 -> getValidationOfContactInformation()
            3 -> getValidationOfBusinessShipAddress()
            4 -> getValidationOfIdentificationAndTaxDetails()
            5 -> getValidationOfBusinessAndAccountInfo()
            6 -> getValidationOfPaymentAndFinancials()
        }

    }

    private fun getValidationOfPaymentAndFinancials() {
        TODO("Not yet implemented")
    }

    private fun getValidationOfBusinessAndAccountInfo() {
        TODO("Not yet implemented")
    }

    private fun getValidationOfIdentificationAndTaxDetails() {
        TODO("Not yet implemented")
    }

    private fun getValidationOfBusinessShipAddress() {
        TODO("Not yet implemented")
    }

    private fun getValidationOfContactInformation() {
        TODO("Not yet implemented")
    }


    fun getValidationOfBusinessPartnerDetails() {
        if (_customerCode.value?.isBlank() == true) {
            _customerCodeValid.value = true
        } else if (_companyName.value?.isBlank() == true) {
            _companyNameValid.value = true
        } else if (_customerPinCode.value.isNullOrEmpty()) {
            _customerPinCodeValid.value = true
        } else if (_customerCategory.value.isNullOrEmpty()) {
            _customerCategoryValid.value = true
        } else if (_country.value.isNullOrEmpty()) {
            _countryValid.value = true
        } else if (_state.value.isNullOrEmpty()) {
            _stateValid.value = true
        } else if (_city.value.isNullOrEmpty()) {
            _cityValid.value = true
        } else if (_leadSource.value.isNullOrEmpty()) {
            _leadSourceValid.value = true
        } else if (_address.value.isNullOrEmpty()) {
            _addressValid.value = true
        }


    }

}