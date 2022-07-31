package com.mahmoudashraf.freshfoodapp.domain.interactor

import com.mahmoudashraf.freshfoodapp.data.entities.ProductsResponse
import com.mahmoudashraf.freshfoodapp.domain.datasource.remote.FreshFoodRemoteDataSource
import io.reactivex.rxjava3.core.Single

class FreshFoodInterActor(private val freshFoodRemoteDataSource: FreshFoodRemoteDataSource) {
    fun getFreshProducts(): Single<ProductsResponse> = freshFoodRemoteDataSource.getFreshProducts()
}