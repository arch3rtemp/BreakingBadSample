package com.example.breakingbadsample.app.db.daos

import androidx.room.Dao
import androidx.room.Transaction
import com.example.breakingbadsample.app.db.Database
import com.example.breakingbadsample.domain.models.CharacterModel

@Dao
abstract class CharactersTransactionsDao(private val database: Database) {

    @Transaction
    open fun updateAll(arg: List<CharacterModel>): Boolean {
        database.charactersDao().deleteAll()
        database.charactersDao().insertAll(arg)
        return true
    }
}