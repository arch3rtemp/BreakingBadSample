package com.example.breakingbadsample.domain.data_providers.local

import com.example.breakingbadsample.app.db.Database
import com.example.breakingbadsample.domain.models.CharacterModel
import io.reactivex.Observable

class LocalDataProviderImpl(private val database: Database) : LocalDataProvider {
    override fun saveCharacters(arg: List<CharacterModel>): Observable<Boolean> {
        return Observable.fromCallable {
            database.charactersTransactionsDao().updateAll(arg)
        }
    }

    override fun selectCharacters(): Observable<List<CharacterModel>> {
        return database.charactersDao().selectAll().toObservable()
    }

    override fun selectCharacterById(id: Int): Observable<CharacterModel> {
        return database.charactersDao().selectById(id).toObservable()
    }
}