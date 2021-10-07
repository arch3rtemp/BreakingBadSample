package com.example.breakingbadsample.app.koin_modules

import com.example.breakingbadsample.domain.use_cases.CacheCharactersUseCase
import com.example.breakingbadsample.domain.use_cases.FetchCharactersUseCase
import com.example.breakingbadsample.domain.use_cases.SaveCharactersUseCase
import com.example.breakingbadsample.domain.use_cases.SelectCharactersUseCase
import org.koin.dsl.module

val USE_CASES_MODULE = module {
    factory {
        FetchCharactersUseCase(get())
    }
    factory {
        SaveCharactersUseCase(get())
    }
    factory {
        CacheCharactersUseCase(get(), get())
    }
    factory {
        SelectCharactersUseCase(get())
    }
}