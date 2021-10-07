package com.example.breakingbadsample.domain.use_cases

import com.example.breakingbadsample.domain.use_cases.base.BaseUseCase
import io.reactivex.Observable

class CacheCharactersUseCase(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val saveCharactersUseCase: SaveCharactersUseCase
) : BaseUseCase<Unit, Boolean> {
    override fun start(arg: Unit?): Observable<Boolean> {
        return fetchCharactersUseCase.start()
            .flatMap { characters ->
                saveCharactersUseCase.start(characters)
            }
    }
}