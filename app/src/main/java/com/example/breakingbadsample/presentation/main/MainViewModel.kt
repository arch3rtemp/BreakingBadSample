package com.example.breakingbadsample.presentation.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.breakingbadsample.domain.use_cases.CacheCharactersUseCase
import com.example.breakingbadsample.domain.use_cases.FetchCharactersUseCase
import com.example.breakingbadsample.domain.use_cases.SelectCharactersUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val cacheCharactersUseCase: CacheCharactersUseCase,
    private val selectCharactersUseCase: SelectCharactersUseCase
) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val liveViewState = MutableLiveData<MainViewState>()

    init {
        selectCharacters()
        fetchCharacters()
    }

    private fun selectCharacters() {
        compositeDisposable.add(
            selectCharactersUseCase
                .start()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    liveViewState.postValue(MainViewState(it))
                }
        )
    }

    private fun fetchCharacters() {
        compositeDisposable.add(
            cacheCharactersUseCase
                .start()
                .onErrorReturn {
                    false
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }
}