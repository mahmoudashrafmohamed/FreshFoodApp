package com.mahmoudashraf.freshfoodapp.presentation.marketplace.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudashraf.freshfoodapp.core.view.loadImage
import com.mahmoudashraf.freshfoodapp.data.entities.BuyersResponse
import com.mahmoudashraf.freshfoodapp.data.entities.BuyersResponseItem
import com.mahmoudashraf.freshfoodapp.databinding.ItemBuyersBinding


class BuyersAdapter(private val data: BuyersResponse) :
    RecyclerView.Adapter<BuyersAdapter.Holder>() {

    class Holder(private val binding: ItemBuyersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(buyersResponseItem: BuyersResponseItem) {
            with(binding) {
               imgBuyer.loadImage(buyersResponseItem.img)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemBuyersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size


}