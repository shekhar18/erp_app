package com.techcognics.erpapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ErpApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}

