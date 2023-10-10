package com.sagar.samplesneakerapp.ui.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.sagar.samplesneakerapp.R
import com.sagar.samplesneakerapp.databinding.ItemSneakerIvBinding
import com.sagar.samplesneakerapp.model.Sneaker


class SneakerImageViewHolder(
    private val binding: ItemSneakerIvBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(sneaker: Sneaker) {
        binding.apply {
            ivSneaker.load(sneaker.media?.thumbUrl) {
                placeholder(R.drawable.shoe)
                transformations(CircleCropTransformation())
                error(R.drawable.shoe)
            }
        }
    }
}