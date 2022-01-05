package com.example.breakingbadsample.app.koin_modules

import com.example.breakingbadsample.domain.use_cases.*
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
    factory {
        SelectCharacterByIdUseCase(get())
    }
    factory {
        SaveFcmTokenUseCase(get())
    }
    factory {
        UpdateFcmTokenUseCase(get())
    }
    factory {
        SetFcmTokenUseCase(get(), get())
    }
}