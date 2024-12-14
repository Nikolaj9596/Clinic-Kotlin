package com.example.clinic

import android.app.Application
import com.example.clinic.data.AppContainer
import com.example.clinic.data.DefaultContainer

class ClinicApplication:Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultContainer()
    }
}