package com.mahmoudashraf.freshfoodapp.presentation.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudashraf.freshfoodapp.core.view.loadImage
import com.mahmoudashraf.freshfoodapp.data.entities.ProductsResponse
import com.mahmoudashraf.freshfoodapp.data.entities.ProductsResponseItem
import com.mahmoudashraf.freshfoodapp.databinding.ItemProductBinding


class ProductsAdapter(private val data: ProductsResponse) :
    RecyclerView.Adapter<ProductsAdapter.Holder>() {

    class Holder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductsResponseItem) {
            with(binding) {
                tvProductName.text = product.name
                tvPromoted.isVisible = product.isPromoted
               imgProduct.loadImage(product.img)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemProductBinding.inflate(
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