package com.techcognics.erpapp.presentation.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.techcognics.erpapp.domain.usecase.ClearSessionUseCase
import com.techcognics.erpapp.domain.usecase.GetTokenUseCase
import com.techcognics.erpapp.domain.usecase.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class MainViewModel @Inject constructor(
    val getTokenUseCase: GetTokenUseCase,
    getUserProfileUseCase: GetUserProfileUseCase,
) : ViewModel() {
    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()
    private val _isLogin = MutableStateFlow(false)
    val isLogin = _isLogin.asStateFlow()/*
    private val _isLogin: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val isLogin: LiveData<Boolean> = _isLogin*/


    init {
        viewModelScope.launch {
            getTokenUseCase.invoke().collect { authToken ->
                Log.d("MAIN", "Token: $authToken")

                if (authToken.isNullOrEmpty()) {
                    // First-time user or logged out
                    _isLogin.value = false
                    delay(1000)
                    _isReady.value = true
                } else {
                    try {
                        // Try to fetch user profile using valid token
                        withContext(Dispatchers.IO) {
                            getUserProfileUseCase(authToken)
                        }
                        _isLogin.value = true
                        delay(1000)
                        _isReady.value = true
                    } catch (e: Exception) {
                        Log.e("MAIN", "Profile fetch failed: ${e.message}")
                        _isLogin.value = false
                        delay(1000)
                        _isReady.value = true
                    }
                }
            }
        }

    }


}
