package com.sagar.samplesneakerapp.ui.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sagar.samplesneakerapp.databinding.SneakerDetailFragmentBinding
import com.sagar.samplesneakerapp.ui.detail.adapter.SneakerImageAdapter
import com.sagar.samplesneakerapp.ui.detail.adapter.SneakerSizeAdapter
import com.sagar.samplesneakerapp.ui.home.adapter.SneakerViewModel
import com.sagar.samplesneakerapp.util.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SneakerDetailFragment : Fragment() {

    private lateinit var binding: SneakerDetailFragmentBinding
    private val sneakerViewModel: SneakerViewModel by viewModels()
    private val args by navArgs<SneakerDetailFragmentArgs>()

    private val sneakerImageAdapter: SneakerImageAdapter by lazy {
        SneakerImageAdapter()
    }
    private val sneakerSizeAdapter: SneakerSizeAdapter by lazy {
        SneakerSizeAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = SneakerDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val sneaker = args.sneaker
            binding.tvSneakerName.text = sneaker?.name
            binding.tvSneakerDes.text = sneaker?.brand
            rvSneakers.adapter = sneakerImageAdapter
            rvSneakerSize.adapter = sneakerSizeAdapter
            btnCart.setOnClickListener {
                sneaker?.let {
                    sneakerViewModel.addToCart(it)
                }
            }
        }

        collectLatestLifecycleFlow(sneakerViewModel.sneakerAdded, viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "Sneaker Added to Cart", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}