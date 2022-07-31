package com.mahmoudashraf.freshfoodapp.presentation.orders.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudashraf.freshfoodapp.core.view.loadImage
import com.mahmoudashraf.freshfoodapp.data.entities.OrdersResponse
import com.mahmoudashraf.freshfoodapp.data.entities.OrdersResponseItem
import com.mahmoudashraf.freshfoodapp.data.entities.ProductsResponse
import com.mahmoudashraf.freshfoodapp.data.entities.ProductsResponseItem
import com.mahmoudashraf.freshfoodapp.databinding.ItemOrderBinding
import com.mahmoudashraf.freshfoodapp.databinding.ItemProductBinding


class OrdersAdapter(private val data: OrdersResponse) :
    RecyclerView.Adapter<OrdersAdapter.Holder>() {

    class Holder(private val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(order: OrdersResponseItem) {
            with(binding) {
                tvOrderId.text = order.id
                tvOrderStatus.text = order.state
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemOrderBinding.inflate(
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