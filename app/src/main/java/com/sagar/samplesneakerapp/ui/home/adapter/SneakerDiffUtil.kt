package com.sagar.samplesneakerapp.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sagar.samplesneakerapp.model.Sneaker


object SneakerDiffUtil : DiffUtil.ItemCallback<Sneaker>() {
    override fun areItemsTheSame(oldItem: Sneaker, newItem: Sneaker): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Sneaker, newItem: Sneaker): Boolean {
        return oldItem == newItem
    }
}