package com.mahmoudashraf.freshfoodapp.data.entities
import com.google.gson.annotations.SerializedName


class BuyersResponse : ArrayList<BuyersResponseItem>()

data class BuyersResponseItem(
    @SerializedName("img")
    val img: String
)