package com.techcognics.erpapp.presentation.screens.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcognics.erpapp.data.registration_data.RegistrationResponse
import com.techcognics.erpapp.domain.usecase.RegistrationUseCase
import com.techcognics.erpapp.presentation.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class RegistrationViewModel @Inject constructor(val registrationUseCase: RegistrationUseCase) :
    ViewModel() {

    private val REGISTRATION_TAG = "RegistrationViewModel"
    private val _registrationState = MutableLiveData<Result<RegistrationResponse>>(Result.Idle)
    val registrationState: LiveData<Result<RegistrationResponse>> = _registrationState

    private val _email: MutableLiveData<String> = MutableLiveData<String>("")
    val email: LiveData<String> = _email

    private val _password: MutableLiveData<String> = MutableLiveData<String>("")
    val password: LiveData<String> = _password

    private val _countryName: MutableLiveData<String> = MutableLiveData<String>("")
    val countryName: LiveData<String> = _countryName

    private val _contactPerson: MutableLiveData<String> = MutableLiveData<String>("")
    val contactPerson: LiveData<String> = _contactPerson

    private val _companyName: MutableLiveData<String> = MutableLiveData<String>("")
    val companyName: LiveData<String> = _companyName

    private val _mobileNumber: MutableLiveData<String> = MutableLiveData<String>("")
    val mobileNumber: LiveData<String> = _mobileNumber


    fun updateEmail(updatedText: String) {
        _email.value = updatedText

    }

    fun updatePassword(updatedText: String) {
        _password.value = updatedText

    }

    fun updateCountryName(updatedText: String) {
        _countryName.value = updatedText

    }

    fun updateContactPerson(updatedText: String) {
        _contactPerson.value = updatedText

    }

    fun updateCompanyName(updatedText: String) {
        _companyName.value = updatedText

    }

    fun updateMobileNumber(updatedText: String) {
        _mobileNumber.value = updatedText

    }

    fun reset() {
        _registrationState.value = Result.Idle
    }


    fun submitDate() {
        viewModelScope.launch(Dispatchers.IO) {
            _registrationState.postValue(Result.Loading)
            try {
                _registrationState.postValue(
                    Result.Success(
                        registrationUseCase.invoke(
                            email = email.value.toString(),
                            mobileNumber = mobileNumber.value.toString(),
                            password = password.value.toString(),
                            companyName = companyName.value.toString(),
                            contactPerson = contactPerson.value.toString(),
                            countryName = companyName.value.toString()
                        )
                    )
                )
            } catch (e: Exception) {
                _registrationState.postValue(Result.Error(e.toString()))
            }

        }
    }


}