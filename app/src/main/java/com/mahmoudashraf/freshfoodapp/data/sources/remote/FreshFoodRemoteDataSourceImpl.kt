package com.mahmoudashraf.freshfoodapp.data.sources.remote

import com.google.gson.Gson
import com.mahmoudashraf.freshfoodapp.core.json.getJSONString
import com.mahmoudashraf.freshfoodapp.data.entities.BuyersResponse
import com.mahmoudashraf.freshfoodapp.data.entities.ProductsResponse
import com.mahmoudashraf.freshfoodapp.domain.datasource.remote.FreshFoodRemoteDataSource
import io.reactivex.rxjava3.core.Single

const val FRESH_PRODUCTS_JSON_FILE_PATH = "FreshFoodProducts.json"
const val BUYERS_JSON_FILE_PATH = "Buyers.json"

class FreshFoodRemoteDataSourceImpl : FreshFoodRemoteDataSource {
    override fun getFreshProducts(): Single<ProductsResponse> {
        val data = Gson().fromJson(
            getJSONString(FRESH_PRODUCTS_JSON_FILE_PATH),
            ProductsResponse::class.java
        )
        return Single.just(data)
    }

    override fun getBuyers(): Single<BuyersResponse> {
        val data = Gson().fromJson(
            getJSONString(BUYERS_JSON_FILE_PATH),
            BuyersResponse::class.java
        )
        return Single.just(data)
    }

}


