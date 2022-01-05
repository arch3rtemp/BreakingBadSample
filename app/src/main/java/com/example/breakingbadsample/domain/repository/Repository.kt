package com.example.breakingbadsample.domain.repository

import com.example.breakingbadsample.domain.models.CharacterModel
import io.reactivex.Observable

interface Repository {
    fun fetchAllCharacters(): Observable<List<CharacterModel>>
    fun saveCharacters(arg: List<CharacterModel>): Observable<Boolean>
    fun selectCharacters(): Observable<List<CharacterModel>>
    fun selectCharacterById(id: Int): Observable<CharacterModel>
    fun updateFcmToken(arg: String): Observable<Boolean>
    fun saveFcmToken(arg: String): Observable<Boolean>
}