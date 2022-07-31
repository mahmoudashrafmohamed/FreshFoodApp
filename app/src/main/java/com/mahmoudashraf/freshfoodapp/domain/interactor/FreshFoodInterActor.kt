package com.mahmoudashraf.freshfoodapp.domain.interactor

import com.mahmoudashraf.freshfoodapp.data.entities.BuyersResponse
import com.mahmoudashraf.freshfoodapp.data.entities.OrdersResponse
import com.mahmoudashraf.freshfoodapp.data.entities.ProductsResponse
import com.mahmoudashraf.freshfoodapp.domain.datasource.remote.FreshFoodRemoteDataSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FreshFoodInterActor @Inject constructor(private val freshFoodRemoteDataSource: FreshFoodRemoteDataSource) {
    fun getFreshProducts(): Single<ProductsResponse> = freshFoodRemoteDataSource.getFreshProducts()
    fun getBuyers(): Single<BuyersResponse> = freshFoodRemoteDataSource.getBuyers()
    fun getOrders(): Single<OrdersResponse> = freshFoodRemoteDataSource.getOrders()
}