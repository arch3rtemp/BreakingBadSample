package com.example.breakingbadsample.presentation.main.characters

import com.example.breakingbadsample.domain.models.CharacterModel

data class CharactersViewState(
    val charactersList: List<CharacterModel>? = null,
    val showLoader: Boolean = false
)