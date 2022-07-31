package com.mahmoudashraf.freshfoodapp.data.entities
import com.google.gson.annotations.SerializedName

class ProductsResponse : ArrayList<ProductsResponseItem>()

data class ProductsResponseItem(
    @SerializedName("img")
    val img: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("isPromoted")
    val isPromoted: Boolean
)

