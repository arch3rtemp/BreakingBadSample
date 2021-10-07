package com.example.breakingbadsample.domain.use_cases.base

import io.reactivex.Observable

interface BaseUseCase<ARG_TYPE, RETURN_TYPE> {
    fun start(arg: ARG_TYPE?=null): Observable<RETURN_TYPE>
}