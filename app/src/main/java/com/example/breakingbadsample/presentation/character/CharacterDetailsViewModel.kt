package com.example.breakingbadsample.presentation.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.breakingbadsample.domain.use_cases.SelectCharacterByIdUseCase
import com.example.breakingbadsample.presentation.main.MainViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharacterDetailsViewModel(
    private val id: Int,
    private val selectCharacterByIdUseCase: SelectCharacterByIdUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val liveViewState = MutableLiveData<CharacterDetailsViewState>()

    init {
        compositeDisposable.add(
            selectCharacterByIdUseCase.start(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    liveViewState.postValue(CharacterDetailsViewState(it))
                }
        )
    }
}