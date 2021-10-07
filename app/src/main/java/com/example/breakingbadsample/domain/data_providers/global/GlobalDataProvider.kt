package com.example.breakingbadsample.domain.data_providers.global

import com.example.breakingbadsample.domain.models.CharacterModel
import io.reactivex.Observable
import retrofit2.http.GET

interface GlobalDataProvider {

    @GET("characters")
    fun fetchAllCharacters(): Observable<List<CharacterModel>>
}