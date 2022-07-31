package com.mahmoudashraf.freshfoodapp.presentation.marketplace.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mahmoudashraf.freshfoodapp.data.entities.BuyersResponse
import com.mahmoudashraf.freshfoodapp.databinding.FragmentMarketplaceBinding
import com.mahmoudashraf.freshfoodapp.presentation.marketplace.view.adapter.BuyersAdapter
import com.mahmoudashraf.freshfoodapp.presentation.marketplace.viewmodel.MarketplaceState
import com.mahmoudashraf.freshfoodapp.presentation.marketplace.viewmodel.MarketplaceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarketplaceFragment : Fragment() {

    private var _binding: FragmentMarketplaceBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MarketplaceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarketplaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.screenState.observe(viewLifecycleOwner,::onScreenStateChange)
    }

    private fun onScreenStateChange(state: MarketplaceState) {
        when (state) {
            is MarketplaceState.Loading -> showLoading()
            is MarketplaceState.Success -> handleSuccessState(state.buyers)
            is MarketplaceState.Error -> handleError(state.message)
        }
    }

    private fun handleError(message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoading() {
        binding.progressBar.isVisible = true
    }

    private fun hideLoading() {
        binding.progressBar.isVisible = false
    }

    private fun handleSuccessState(buyers: BuyersResponse) {
        hideLoading()
        binding.rvBuyers.adapter = BuyersAdapter(buyers)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}