package com.sagar.samplesneakerapp.ui.cart.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.sagar.samplesneakerapp.R
import com.sagar.samplesneakerapp.databinding.ItemCartBinding
import com.sagar.samplesneakerapp.model.Sneaker


class CartViewHolder(
    private val binding: ItemCartBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(sneaker: Sneaker) {
        binding.apply {
            tvSneakerName.text = sneaker.name
            tvPrice.text = sneaker.retailPrice.toString()
            ivSneaker.load(sneaker.media?.thumbUrl) {
                placeholder(R.drawable.shoe)
                transformations(CircleCropTransformation())
                error(R.drawable.shoe)
            }
        }
    }
}