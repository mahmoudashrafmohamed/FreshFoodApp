package com.mahmoudashraf.freshfoodapp.domain.datasource.remote

import com.mahmoudashraf.freshfoodapp.data.entities.BuyersResponse
import com.mahmoudashraf.freshfoodapp.data.entities.ProductsResponse
import io.reactivex.rxjava3.core.Single

interface FreshFoodRemoteDataSource {
fun getFreshProducts() : Single<ProductsResponse>
fun getBuyers() : Single<BuyersResponse>
}