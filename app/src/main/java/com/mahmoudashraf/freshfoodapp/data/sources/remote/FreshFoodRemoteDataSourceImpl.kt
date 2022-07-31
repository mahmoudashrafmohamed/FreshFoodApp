package com.mahmoudashraf.freshfoodapp.data.sources.remote

import com.google.gson.Gson
import com.mahmoudashraf.freshfoodapp.data.entities.ProductsResponse
import com.mahmoudashraf.freshfoodapp.domain.datasource.remote.FreshFoodRemoteDataSource
import io.reactivex.rxjava3.core.Single

class FreshFoodRemoteDataSourceImpl : FreshFoodRemoteDataSource {
    override fun getFreshProducts(): Single<ProductsResponse> {
        val data = Gson().fromJson("",ProductsResponse::class.java)
        return Single.just(data)
    }
}