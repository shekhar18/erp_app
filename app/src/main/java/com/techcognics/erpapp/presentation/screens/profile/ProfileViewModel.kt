package com.techcognics.erpapp.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcognics.erpapp.domain.usecase.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state

    init {
        fetchProfiles()
    }

    private fun fetchProfiles() {
        viewModelScope.launch {
            try {
                _state.value = ProfileState(isLoading = true)
                val profiles = repository.getProfiles()
                _state.value = ProfileState(profileList = profiles)
            } catch (e: Exception) {
                _state.value = ProfileState(error = e.message ?: "Unknown Error")
            }
        }
    }
}