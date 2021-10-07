package com.example.breakingbadsample.app.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.breakingbadsample.domain.models.CharacterModel
import io.reactivex.Flowable

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: List<CharacterModel>)

    @Query("DELETE FROM characters")
    fun deleteAll()

    @Query("SELECT * FROM characters")
    fun selectAll(): Flowable<List<CharacterModel>>

}