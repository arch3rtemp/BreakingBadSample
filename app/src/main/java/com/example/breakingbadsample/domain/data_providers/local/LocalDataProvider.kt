package com.example.breakingbadsample.domain.data_providers.local

import com.example.breakingbadsample.domain.models.CharacterModel
import io.reactivex.Observable

interface LocalDataProvider {
    fun saveCharacters(arg: List<CharacterModel>): Observable<Boolean>
    fun selectCharacters(): Observable<List<CharacterModel>>
}