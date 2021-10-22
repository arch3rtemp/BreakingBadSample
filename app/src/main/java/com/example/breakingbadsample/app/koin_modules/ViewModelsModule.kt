package com.example.breakingbadsample.app.koin_modules

import com.example.breakingbadsample.domain.models.data_models.CharacterDetailsArgModel
import com.example.breakingbadsample.presentation.main.character_details.CharacterDetailsViewModel
import com.example.breakingbadsample.presentation.main.MainViewModel
import com.example.breakingbadsample.presentation.main.characters.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val VIEW_MODELS_MODULE = module {
    viewModel {
        MainViewModel()
    }

    viewModel { (arg: CharacterDetailsArgModel) ->
        CharacterDetailsViewModel(arg, get())
    }

    viewModel {
        CharactersViewModel(get(), get())
    }
}