package com.example.breakingbadsample.app.koin_modules

import com.example.breakingbadsample.presentation.character.CharacterDetailsViewModel
import com.example.breakingbadsample.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val VIEW_MODELS_MODULE = module {
    viewModel {
        MainViewModel(get(), get())
    }

    viewModel { (id: Int) ->
        CharacterDetailsViewModel(id, get())
    }
}