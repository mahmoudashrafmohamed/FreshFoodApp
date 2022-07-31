package com.mahmoudashraf.freshfoodapp.presentation.orders.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahmoudashraf.freshfoodapp.data.entities.BuyersResponse
import com.mahmoudashraf.freshfoodapp.data.entities.OrdersResponse
import com.mahmoudashraf.freshfoodapp.domain.interactor.FreshFoodInterActor
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(private val interActor: FreshFoodInterActor) :
    ViewModel() {

    val screenState by lazy { MutableLiveData<OrdersState>() }

    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    init {
        loadOrders()
    }


    private fun loadOrders() {
        interActor.getOrders()
            .delay(3000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { screenState.postValue(OrdersState.Loading) }
            .map { OrdersState.Success(it) as OrdersState }
            .onErrorReturn { OrdersState.Error(it.message ?: "Something wrong happened!") }
            .subscribe(screenState::postValue, Throwable::printStackTrace)
            .also { addDisposable(it) }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

sealed class OrdersState {
    object Loading : OrdersState()
    data class Success(val orders : OrdersResponse) : OrdersState()
    data class Error(val message: String) : OrdersState()
}
