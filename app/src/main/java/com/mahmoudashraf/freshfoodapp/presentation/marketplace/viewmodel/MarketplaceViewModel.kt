package com.mahmoudashraf.freshfoodapp.presentation.marketplace.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahmoudashraf.freshfoodapp.data.entities.BuyersResponse
import com.mahmoudashraf.freshfoodapp.domain.interactor.FreshFoodInterActor
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MarketplaceViewModel @Inject constructor(private val interActor: FreshFoodInterActor) :
    ViewModel() {

    val screenState by lazy { MutableLiveData<MarketplaceState>() }

    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    init {
        loadBuyers()
    }


    private fun loadBuyers() {
        interActor.getBuyers()
            .delay(3000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { screenState.postValue(MarketplaceState.Loading) }
            .map { MarketplaceState.Success(it) as MarketplaceState }
            .onErrorReturn { MarketplaceState.Error(it.message ?: "Something wrong happened!") }
            .subscribe(screenState::postValue, Throwable::printStackTrace)
            .also { addDisposable(it) }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

sealed class MarketplaceState {
    object Loading : MarketplaceState()
    data class Success(val buyers: BuyersResponse) : MarketplaceState()
    data class Error(val message: String) : MarketplaceState()
}
