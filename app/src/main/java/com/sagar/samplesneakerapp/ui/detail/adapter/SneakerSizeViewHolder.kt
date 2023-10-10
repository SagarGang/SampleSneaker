package com.sagar.samplesneakerapp.ui.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sagar.samplesneakerapp.databinding.ItemSneakerSizeBinding


class SneakerSizeViewHolder(
    private val binding: ItemSneakerSizeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(size: Int) {
        binding.apply {
            tvSize.text = (size.plus(6)).toString()
        }
    }
}