package com.example.breakingbadsample.app.koin_modules

import com.example.breakingbadsample.domain.repository.Repository
import com.example.breakingbadsample.domain.repository.RepositoryImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val REPOSITORY_MODULE = module {
    single {
        RepositoryImpl(get(), get())
    } bind Repository::class
}