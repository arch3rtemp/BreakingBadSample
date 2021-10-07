package com.example.breakingbadsample.presentation.main

import com.example.breakingbadsample.domain.models.CharacterModel

data class MainViewState(
    val charactersList: List<CharacterModel>? = null,
    val showLoader: Boolean = false
)
