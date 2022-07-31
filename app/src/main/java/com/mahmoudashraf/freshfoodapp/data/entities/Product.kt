package com.mahmoudashraf.freshfoodapp.data.entities
import com.google.gson.annotations.SerializedName


data class Product(
    @SerializedName("img")
    val img: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("isPromoted")
    val isPromoted: Boolean
)

data class ProductsResponse(val data : List<Product>)