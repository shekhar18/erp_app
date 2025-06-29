package com.techcognics.erpapp.presentation.screens.company_dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CompanyDashboardScreenViewModel @Inject constructor() : ViewModel() {

    private val _startDate: MutableLiveData<String> = MutableLiveData<String>()
    val startDate: LiveData<String> = _startDate

    private val _endDate: MutableLiveData<String> = MutableLiveData<String>()
    val endDate: LiveData<String> = _endDate

    private val _docNo: MutableLiveData<String> = MutableLiveData<String>("D09")
    val docNo: LiveData<String> = _docNo


    init {
        //implement api Call
    }


    fun updateStartDate(startDate: String) {
        Log.d("CDVMS",startDate)
        _startDate.value = startDate
    }

    fun updateEndString(endDate: String) {
        Log.d("CDVMS",endDate)
        _endDate.value = endDate
    }


}