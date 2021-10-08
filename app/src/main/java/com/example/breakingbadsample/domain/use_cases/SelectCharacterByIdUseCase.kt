package com.example.breakingbadsample.domain.use_cases

import com.example.breakingbadsample.domain.models.CharacterModel
import com.example.breakingbadsample.domain.repository.Repository
import com.example.breakingbadsample.domain.use_cases.base.BaseUseCase
import io.reactivex.Observable

class SelectCharacterByIdUseCase(private val repo: Repository) :
    BaseUseCase<Int, CharacterModel> {
    override fun start(arg: Int?): Observable<CharacterModel> {
        return repo.selectCharacterById(arg!!)
    }
}