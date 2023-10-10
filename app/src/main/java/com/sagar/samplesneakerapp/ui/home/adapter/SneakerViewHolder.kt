package com.sagar.samplesneakerapp.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.sagar.samplesneakerapp.R
import com.sagar.samplesneakerapp.databinding.ItemSneakerBinding
import com.sagar.samplesneakerapp.model.Sneaker


class SneakerViewHolder(
    private val onAdd: (Sneaker) -> Unit,
    private val onItemClick: (Sneaker) -> Unit,
    private val binding: ItemSneakerBinding
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(sneaker: Sneaker) {
        binding.apply {
            tvSneakerName.text = sneaker.name
            tvPrice.text = sneaker.retailPrice.toString()
            ivSneaker.load(sneaker.media?.thumbUrl) {
                placeholder(R.drawable.shoe)
                transformations(CircleCropTransformation())
                error(R.drawable.shoe)
            }
            ivAdd.setOnClickListener {
                onAdd.invoke(sneaker)
            }
            binding.root.setOnClickListener {
                onItemClick.invoke(sneaker)
            }
        }
    }
}