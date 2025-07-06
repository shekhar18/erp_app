package com.techcognics.erpapp

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ProcessLifecycleOwner
import com.techcognics.erpapp.presentation.lifecycle.AppLifecycleEntryPoint
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ErpApp : Application() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        val entryPoint = EntryPointAccessors.fromApplication(
            this,
            AppLifecycleEntryPoint::class.java
        )
        val appLifecycleObserver = entryPoint.getAppLifecycleObserver()

        ProcessLifecycleOwner.get().lifecycle.addObserver(appLifecycleObserver)
    }
}

