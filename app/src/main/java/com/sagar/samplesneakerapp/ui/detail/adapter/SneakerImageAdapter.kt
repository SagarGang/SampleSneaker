package com.sagar.samplesneakerapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sagar.samplesneakerapp.databinding.ItemSneakerIvBinding

class SneakerImageAdapter : RecyclerView.Adapter<SneakerImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SneakerImageViewHolder {
        val binding =
            ItemSneakerIvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SneakerImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: SneakerImageViewHolder, position: Int) {

    }

}