package com.example.wiremock_stages_compose

import android.app.Application
import com.example.wiremock_stages_compose.domain.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@KoinApp)
            // Load modules
            modules(koinModules)
        }
    }
}