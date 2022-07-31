package com.mahmoudashraf.freshfoodapp.presentation

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){
    companion object {
        lateinit var appContext: Context
    }
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}