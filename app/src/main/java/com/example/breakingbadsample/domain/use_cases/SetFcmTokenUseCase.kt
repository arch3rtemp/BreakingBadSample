package com.example.breakingbadsample.domain.use_cases

import com.example.breakingbadsample.domain.use_cases.base.BaseUseCase
import io.reactivex.Observable

class SetFcmTokenUseCase(
    private val saveFcmTokenUseCase: SaveFcmTokenUseCase,
    private val updateFcmTokenUseCase: UpdateFcmTokenUseCase
) : BaseUseCase<String, Boolean> {
    override fun start(arg: String?): Observable<Boolean> {
        return saveFcmTokenUseCase.start(arg).flatMap {
            updateFcmTokenUseCase.start(arg).
                    onErrorReturn { false }
        }
    }
}