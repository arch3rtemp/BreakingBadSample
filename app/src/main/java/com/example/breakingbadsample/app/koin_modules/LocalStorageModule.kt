package com.example.breakingbadsample.app.koin_modules

import android.content.Context
import androidx.room.Room
import com.example.breakingbadsample.app.db.Database
import com.example.breakingbadsample.domain.data_providers.local.LocalDataProvider
import com.example.breakingbadsample.domain.data_providers.local.LocalDataProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val LOCAL_STORAGE_MODULE = module {
    single {
        Room.databaseBuilder(androidContext(), Database::class.java, "test_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        androidContext().getSharedPreferences("key", Context.MODE_PRIVATE)
    }

    single {
        LocalDataProviderImpl(get(), get())
    } bind LocalDataProvider::class
}