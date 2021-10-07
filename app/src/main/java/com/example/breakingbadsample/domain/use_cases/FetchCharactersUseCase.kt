package com.example.breakingbadsample.domain.use_cases

import com.example.breakingbadsample.domain.models.CharacterModel
import com.example.breakingbadsample.domain.repository.Repository
import com.example.breakingbadsample.domain.use_cases.base.BaseUseCase
import io.reactivex.Observable

class FetchCharactersUseCase(private val repository: Repository) :
    BaseUseCase<Unit, List<CharacterModel>> {
    override fun start(arg: Unit?): Observable<List<CharacterModel>> {
        return repository.fetchAllCharacters()
    }
}