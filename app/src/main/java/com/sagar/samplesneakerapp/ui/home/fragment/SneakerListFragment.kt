package com.sagar.samplesneakerapp.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sagar.samplesneakerapp.databinding.SneakerListFragmentBinding
import com.sagar.samplesneakerapp.model.Sneaker
import com.sagar.samplesneakerapp.ui.home.adapter.SneakerAdapter
import com.sagar.samplesneakerapp.ui.home.adapter.SneakerViewModel
import com.sagar.samplesneakerapp.util.collectLatestLifecycleFlow
import com.sagar.samplesneakerapp.util.getTextChangeFlow
import com.sagar.samplesneakerapp.util.hide
import com.sagar.samplesneakerapp.util.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SneakerListFragment : Fragment() {

    private lateinit var binding: SneakerListFragmentBinding
    private val sneakerViewModel: SneakerViewModel by viewModels()

    private val sneakerAdapter: SneakerAdapter by lazy {
        SneakerAdapter(::onAdd, ::onDetails)
    }

    private fun onDetails(sneaker: Sneaker) {
        findNavController().navigate(
            SneakerListFragmentDirections.actionSneakerListToSneakerDetail(
                sneaker
            )
        )
    }

    private fun onAdd(sneaker: Sneaker) {
        sneakerViewModel.addToCart(sneaker)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SneakerListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sneakerViewModel.getSneakers()
        binding.rvSneakers.adapter = sneakerAdapter
        sneakerViewModel.observeSearch(binding.etSearch.getTextChangeFlow())
        collectLatestLifecycleFlow(sneakerViewModel.sneakerList, viewLifecycleOwner) {
            sneakerAdapter.submitList(it)
        }
        collectLatestLifecycleFlow(sneakerViewModel.sneakerAdded, viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "Sneaker Added to Cart", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        collectLatestLifecycleFlow(sneakerViewModel.searchQuery, viewLifecycleOwner) {
            if (binding.etSearch.isVisible) {
                sneakerAdapter.filter.filter(it)
            }
        }
        with(binding) {
            ivSearch.setOnClickListener {
                etSearch.show()
                ivClose.show()
                ivSearch.hide()
                tvAppName.hide()
            }
            ivClose.setOnClickListener {
                etSearch.setText("")
                sneakerAdapter.filter.filter("")
                etSearch.hide()
                ivClose.hide()
                ivSearch.show()
                tvAppName.show()
            }
        }
    }
}


