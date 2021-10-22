package com.example.breakingbadsample.app.koin_modules

import com.example.breakingbadsample.R
import com.example.breakingbadsample.app.consts.NodeNames
import com.example.breakingbadsample.domain.data_providers.global.GlobalDataProvider
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val NETWORKING_MODULE = module {

    single(named(NodeNames.NODE_BASE_URL)) {
        androidContext().getString(R.string.base_url)
    }

    single {
        OkHttpClient.Builder().build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named(NodeNames.NODE_BASE_URL)))
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(GlobalDataProvider::class.java)
    }
}