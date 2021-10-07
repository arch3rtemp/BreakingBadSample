package com.example.breakingbadsample.app

import android.app.Application
import com.example.breakingbadsample.app.koin_modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                NETWORKING_MODULE,
                VIEW_MODELS_MODULE,
                LOCAL_STORAGE_MODULE,
                REPOSITORY_MODULE,
                USE_CASES_MODULE
            )
        }
    }

}