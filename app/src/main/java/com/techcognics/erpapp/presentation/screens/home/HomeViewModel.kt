package com.techcognics.erpapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcognics.erpapp.domain.usecase.ClearSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val clearSessionUseCase: ClearSessionUseCase):ViewModel() {



    fun getSignOut(){
        viewModelScope.launch {
            clearSessionUseCase.invoke()
        }


    }


}