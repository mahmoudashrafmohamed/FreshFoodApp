package com.mahmoudashraf.freshfoodapp.presentation.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahmoudashraf.freshfoodapp.data.entities.ProductsResponse
import com.mahmoudashraf.freshfoodapp.domain.interactor.FreshFoodInterActor
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val interActor: FreshFoodInterActor) : ViewModel() {

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
            .delay(3000,TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { screenState.postValue(HomeState.Loading) }
            .map { HomeState.Success(it) as HomeState }
            .onErrorReturn { HomeState.Error(it.message ?: "Something wrong happened!") }
            .subscribe(screenState::postValue, Throwable::printStackTrace)
            .also { addDisposable(it) }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}

sealed class HomeState {
    object Loading : HomeState()
    data class Success(val products: ProductsResponse) : HomeState()
    data class Error(val message: String) : HomeState()
}
