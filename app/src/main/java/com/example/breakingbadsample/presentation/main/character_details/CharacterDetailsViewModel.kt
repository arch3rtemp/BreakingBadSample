package com.example.breakingbadsample.presentation.main.character_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.breakingbadsample.domain.models.data_models.CharacterDetailsArgModel
import com.example.breakingbadsample.domain.use_cases.SelectCharacterByIdUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharacterDetailsViewModel(
    private val arg: CharacterDetailsArgModel,
    private val selectCharacterByIdUseCase: SelectCharacterByIdUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val liveViewState = MutableLiveData<CharacterDetailsViewState>()

    init {
        compositeDisposable.add(
            selectCharacterByIdUseCase.start(arg.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    liveViewState.postValue(CharacterDetailsViewState(it))
                }
        )
    }
}