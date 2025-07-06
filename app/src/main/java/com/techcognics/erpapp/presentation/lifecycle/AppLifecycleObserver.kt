package com.techcognics.erpapp.presentation.lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.techcognics.erpapp.domain.usecase.ClearSessionUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppLifecycleObserver @Inject constructor(val clearSessionUseCase: ClearSessionUseCase) :
    DefaultLifecycleObserver {

    override fun onStop(owner: LifecycleOwner) {
        CoroutineScope(Dispatchers.IO).launch {
            clearSessionUseCase()
            Log.d("AppLifecycleObserver", "User session cleared on background.")
        }
    }
}