package com.mahmoudashraf.freshfoodapp.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mahmoudashraf.freshfoodapp.data.entities.ProductsResponse
import com.mahmoudashraf.freshfoodapp.databinding.FragmentHomeBinding
import com.mahmoudashraf.freshfoodapp.presentation.home.adapter.ProductsAdapter
import com.mahmoudashraf.freshfoodapp.presentation.home.viewmodel.HomeState
import com.mahmoudashraf.freshfoodapp.presentation.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.screenState.observe(viewLifecycleOwner,::onScreenStateChange)
    }

    private fun onScreenStateChange(state: HomeState) {
        when (state) {
            is HomeState.Loading -> showLoading()
            is HomeState.Success -> handleSuccessState(state.products)
            is HomeState.Error -> handleError(state.message)
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

    private fun handleSuccessState(products: ProductsResponse) {
        hideLoading()
        binding.rvProducts.adapter = ProductsAdapter(products)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}