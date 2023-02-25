package com.example.taskapp.app

import android.app.Application
import com.example.taskapp.utils.Common
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()

        Common.globalContext = this
    }
}