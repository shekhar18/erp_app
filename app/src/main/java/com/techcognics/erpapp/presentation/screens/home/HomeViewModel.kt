package com.techcognics.erpapp.presentation.screens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcognics.erpapp.data.user_roles.MenuResponseItem
import com.techcognics.erpapp.domain.usecase.ClearSessionUseCase
import com.techcognics.erpapp.domain.usecase.GetSaveUserRoleUseCase
import com.techcognics.erpapp.domain.usecase.GetTokenUseCase
import com.techcognics.erpapp.domain.usecase.GetUserRolesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val clearSessionUseCase: ClearSessionUseCase,
    val getSaveUserRoleUseCase: GetSaveUserRoleUseCase,
    val getUserRolesUseCase: GetUserRolesUseCase,
    val getTokenUseCase: GetTokenUseCase,
) : ViewModel() {


    private val _drawerMenuList: MutableLiveData<List<MenuResponseItem>> = MutableLiveData(
        emptyList()
    )
    val drawerMenuList: LiveData<List<MenuResponseItem>> = _drawerMenuList

    init {
        getUserRoles()
    }

    fun getUserRoles() {
        viewModelScope.launch(Dispatchers.IO) {
            getTokenUseCase().collect { token ->
                val menuResponse = getUserRolesUseCase(token = token.toString())
                val userRoles = getSaveUserRoleUseCase()

                val listMenu = menuResponse.mapNotNull { menu ->
                    val filteredChildren = menu.children?.filter { child ->
                        child.authorities.isNotEmpty() && child.authorities.any { it in userRoles }
                    }

                    // Check if parent itself has matching authorities
                    val isParentAuthorized =
                        menu.authorities.isNotEmpty() && menu.authorities.any { it in userRoles }

                    // Include parent if it's authorized or it has any authorized children
                    if (isParentAuthorized || !filteredChildren.isNullOrEmpty()) {
                        menu.copy(
                            children = filteredChildren ?: emptyList()
                        )
                    } else {
                        null
                    }

                }

                _drawerMenuList.postValue(listMenu)


            }
        }
    }

    fun getSignOut() {
        viewModelScope.launch {
            clearSessionUseCase.invoke()
        }


    }


}