package com.example.breakingbadsample.domain.use_cases

import com.example.breakingbadsample.domain.repository.Repository
import com.example.breakingbadsample.domain.use_cases.base.BaseUseCase
import io.reactivex.Observable

class UpdateFcmTokenUseCase(private val repo: Repository) : BaseUseCase<String, Boolean> {
    override fun start(arg: String?): Observable<Boolean> {
        return repo.updateFcmToken(arg!!)
    }
}