package com.mahmoudashraf.freshfoodapp.presentation.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahmoudashraf.freshfoodapp.data.entities.Product
import com.mahmoudashraf.freshfoodapp.domain.interactor.FreshFoodInterActor
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(private val interActor: FreshFoodInterActor) : ViewModel() {

    val screenState by lazy { MutableLiveData<HomeState>() }


    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    init {
        loadProducts()
    }


    private fun loadProducts() {
        interActor.getFreshProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { screenState.postValue(HomeState.Loading) }
            .map { HomeState.Success(it.data) as HomeState }
            .onErrorReturn { HomeState.Error(it) }
            .subscribe(screenState::postValue, Throwable::printStackTrace)
            .also { addDisposable(it) }
    }

}

sealed class HomeState {
    object Loading : HomeState()
    data class Success(val products : List<Product>) : HomeState()
    data class Error(val ex: Throwable) : HomeState()
}
