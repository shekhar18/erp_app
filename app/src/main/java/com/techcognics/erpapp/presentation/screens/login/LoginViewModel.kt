package com.techcognics.erpapp.presentation.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcognics.erpapp.data.login_data.LoginResponse
import com.techcognics.erpapp.domain.usecase.GetSaveUserRoleUseCase
import com.techcognics.erpapp.domain.usecase.GetTokenUseCase
import com.techcognics.erpapp.domain.usecase.GetUserProfileUseCase
import com.techcognics.erpapp.domain.usecase.LoginUseCase
import com.techcognics.erpapp.domain.usecase.SaveTokenUseCase
import com.techcognics.erpapp.domain.usecase.SaveUserRoleUseCase
import com.techcognics.erpapp.presentation.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class LoginViewModel @Inject constructor(
    val loginUseCase: LoginUseCase,
    val saveTokenUseCase: SaveTokenUseCase,
    val getUserProfileUseCase: GetUserProfileUseCase,
    val saveUserRoleUseCase: SaveUserRoleUseCase,
) : ViewModel() {

    private val LOGIN_TAG = "LoginViewModel"

    private val _loginState = MutableLiveData<Result<LoginResponse>>(Result.Idle)
    val loginState: LiveData<Result<LoginResponse>> = _loginState

    private val _userid: MutableLiveData<String> = MutableLiveData<String>("")
    val userId: LiveData<String> = _userid

    private val _userPassword: MutableLiveData<String> = MutableLiveData<String>("")
    val userPassword: LiveData<String> = _userPassword

    private val _rememberCheck: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val rememberCheck: LiveData<Boolean> = _rememberCheck

    fun updateUserid(userId: String) {
        _userid.value = userId
    }

    fun updatePassword(password: String) {
        _userPassword.value = password
    }

    fun updateRememberCheck(isCheck: Boolean) {
        _rememberCheck.value = isCheck
    }

    fun reset() {
        _loginState.value = Result.Idle
    }

    fun getLogin() {/* Log.d(LOGIN_TAG, userId.value.toString())
        Log.d(LOGIN_TAG, userPassword.value.toString())
        Log.d(LOGIN_TAG, rememberCheck.value.toString())
*/
        viewModelScope.launch(Dispatchers.IO) {
            _loginState.postValue(Result.Loading)

            try {
                val response = loginUseCase.invoke(
                    userId.value.toString(),
                    userPassword.value.toString(),
                    rememberCheck.value as Boolean
                )
                Log.d(LOGIN_TAG, response.tokenId)
                saveTokenUseCase.invoke("Bearer ${response.tokenId}")


                val userRoles = getUserProfileUseCase("Bearer ${response.tokenId}").authorities

                saveUserRoleUseCase(userRoleList = userRoles)





                _loginState.postValue(Result.Success(response))

            } catch (e: Exception) {
                _loginState.postValue(Result.Error(e.toString()))
            }
        }
    }


}