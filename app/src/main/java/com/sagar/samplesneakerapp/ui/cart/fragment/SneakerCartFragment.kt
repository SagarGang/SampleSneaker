package com.sagar.samplesneakerapp.ui.cart.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sagar.samplesneakerapp.R
import com.sagar.samplesneakerapp.databinding.CartListFragmentBinding
import com.sagar.samplesneakerapp.model.Sneaker
import com.sagar.samplesneakerapp.ui.cart.adapter.CartAdapter
import com.sagar.samplesneakerapp.ui.home.adapter.SneakerViewModel
import com.sagar.samplesneakerapp.util.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SneakerCartFragment : Fragment() {

    private lateinit var binding: CartListFragmentBinding
    private val sneakerViewModel: SneakerViewModel by viewModels()

    private val cartAdapter: CartAdapter by lazy {
        CartAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CartListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCart.adapter = cartAdapter
        sneakerViewModel.getCartList()
        collectLatestLifecycleFlow(sneakerViewModel.cartList, viewLifecycleOwner) {
            calculateTotalAmount(it)
            cartAdapter.submitList(it.toList())
        }
    }

    private fun calculateTotalAmount(sneakers: List<Sneaker>) {
        var subTotal = 0
        for (sneaker in sneakers) {
            sneaker.retailPrice?.let {
                subTotal += it
            }
        }
        with(binding) {
            tvSubTotal.text = getString(R.string.subtotal, subTotal)
            tvTotal.text = getString(R.string.subtotal, subTotal + 40)
        }
    }
}