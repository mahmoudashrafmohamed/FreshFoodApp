package com.mahmoudashraf.freshfoodapp.data.entities
import com.google.gson.annotations.SerializedName


class OrdersResponse : ArrayList<OrdersResponseItem>()

data class OrdersResponseItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("state")
    val state: String
)