package com.sagar.samplesneakerapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sagar.samplesneakerapp.databinding.ItemSneakerSizeBinding

class SneakerSizeAdapter : RecyclerView.Adapter<SneakerSizeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SneakerSizeViewHolder {
        val binding =
            ItemSneakerSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SneakerSizeViewHolder(binding)
    }

    override fun getItemCount() = 3


    override fun onBindViewHolder(holder: SneakerSizeViewHolder, position: Int) {
        holder.bind(position)
    }
}