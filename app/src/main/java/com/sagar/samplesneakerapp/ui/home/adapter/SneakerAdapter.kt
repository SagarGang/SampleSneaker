package com.sagar.samplesneakerapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.sagar.samplesneakerapp.databinding.ItemSneakerBinding
import com.sagar.samplesneakerapp.model.Sneaker
import java.util.Locale

class SneakerAdapter(
    private val onAdd: (Sneaker) -> Unit,
    private val onItemClick: (Sneaker) -> Unit
) :
    RecyclerView.Adapter<SneakerViewHolder>(), Filterable {
    private val filteredList = ArrayList<Sneaker>()
    private val differ = AsyncListDiffer(this, SneakerDiffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SneakerViewHolder {
        val binding = ItemSneakerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SneakerViewHolder(onAdd, onItemClick, binding)
    }

    override fun onBindViewHolder(holder: SneakerViewHolder, position: Int) {
        val currentProduct = filteredList[position]
        holder.bind(currentProduct)
    }

    override fun getItemCount(): Int = filteredList.size

    fun submitList(sneakers: List<Sneaker>) {
        filteredList.addAll(sneakers)
        differ.submitList(sneakers)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredResults = if (constraint.isNullOrEmpty()) {
                    differ.currentList.toList()
                } else {
                    val filterPattern = constraint.toString().lowercase(Locale.ROOT).trim()
                    differ.currentList.filter { product ->
                        product.name?.lowercase(Locale.ROOT)?.contains(filterPattern) ?: false
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredResults
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                val filteredList = results?.values as? List<Sneaker>
                if (filteredList != null) {
                    this@SneakerAdapter.filteredList.clear()
                    this@SneakerAdapter.filteredList.addAll(filteredList)
                    notifyDataSetChanged()
                }
            }
        }
    }
}