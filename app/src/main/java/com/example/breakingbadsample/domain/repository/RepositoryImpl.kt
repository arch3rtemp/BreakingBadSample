package com.example.breakingbadsample.domain.repository

import com.example.breakingbadsample.domain.data_providers.global.GlobalDataProvider
import com.example.breakingbadsample.domain.data_providers.local.LocalDataProvider
import com.example.breakingbadsample.domain.models.CharacterModel
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class RepositoryImpl(
    private val globalDataProvider: GlobalDataProvider,
    private val localDataProvider: LocalDataProvider
) :
    Repository {

    override fun fetchAllCharacters(): Observable<List<CharacterModel>> {
        return globalDataProvider.fetchAllCharacters()
    }

    override fun saveCharacters(arg: List<CharacterModel>): Observable<Boolean> {
        return localDataProvider.saveCharacters(arg)
    }

    override fun selectCharacters(): Observable<List<CharacterModel>> {
        return localDataProvider.selectCharacters()
    }

    override fun selectCharacterById(id: Int): Observable<CharacterModel> {
        return localDataProvider.selectCharacterById(id)
    }

    override fun updateFcmToken(arg: String): Observable<Boolean> {
        return Observable.timer(2L, TimeUnit.SECONDS).map { true }
    }

    override fun saveFcmToken(arg: String): Observable<Boolean> {
        return localDataProvider.saveFcmToken(arg)
    }
}