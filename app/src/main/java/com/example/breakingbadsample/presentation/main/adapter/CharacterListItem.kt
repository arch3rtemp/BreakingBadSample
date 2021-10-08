package com.example.breakingbadsample.presentation.main.adapter

import com.example.breakingbadsample.domain.models.CharacterModel

sealed class CharacterListItem

data class TitleItem(
    val title: String
) : CharacterListItem()

data class CharacterItem(
    val characterModel: CharacterModel
) : CharacterListItem()