package com.example.breakingbadsample.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.breakingbadsample.app.db.converters.AllTypeConverter
import com.example.breakingbadsample.app.db.daos.CharactersDao
import com.example.breakingbadsample.app.db.daos.CharactersTransactionsDao
import com.example.breakingbadsample.domain.models.CharacterModel

@Database(
    version = 1, exportSchema = false,
    entities = [CharacterModel::class]
)
@TypeConverters(AllTypeConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
    abstract fun charactersTransactionsDao(): CharactersTransactionsDao
}