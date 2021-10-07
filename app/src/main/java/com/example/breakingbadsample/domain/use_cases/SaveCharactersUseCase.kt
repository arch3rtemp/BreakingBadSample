package com.example.breakingbadsample.domain.use_cases

import com.example.breakingbadsample.domain.models.CharacterModel
import com.example.breakingbadsample.domain.repository.Repository
import com.example.breakingbadsample.domain.use_cases.base.BaseUseCase
import io.reactivex.Observable

class SaveCharactersUseCase(private val repository: Repository):BaseUseCase<List<CharacterModel>, Boolean> {
    override fun start(arg: List<CharacterModel>?): Observable<Boolean> {
        return repository.saveCharacters(arg!!)
    }
}