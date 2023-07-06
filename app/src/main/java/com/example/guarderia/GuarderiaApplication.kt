package com.example.guarderia

import android.app.Application
import com.example.guarderia.data.AppContainer
import com.example.guarderia.data.GuarderiaAppContainer

class GuarderiaApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container= GuarderiaAppContainer(context = this)
    }
}