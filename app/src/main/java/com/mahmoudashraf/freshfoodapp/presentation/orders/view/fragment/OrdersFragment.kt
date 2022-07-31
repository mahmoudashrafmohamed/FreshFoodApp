package com.mahmoudashraf.freshfoodapp.presentation.orders.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mahmoudashraf.freshfoodapp.data.entities.OrdersResponse
import com.mahmoudashraf.freshfoodapp.databinding.FragmentOrdersBinding
import com.mahmoudashraf.freshfoodapp.presentation.orders.view.adapter.OrdersAdapter
import com.mahmoudashraf.freshfoodapp.presentation.orders.viewmodel.OrdersState
import com.mahmoudashraf.freshfoodapp.presentation.orders.viewmodel.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : Fragment() {


    private var _binding: FragmentOrdersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: OrdersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.screenState.observe(viewLifecycleOwner,::onScreenStateChange)
    }

    private fun onScreenStateChange(state: OrdersState) {
        when (state) {
            is OrdersState.Loading -> showLoading()
            is OrdersState.Success -> handleSuccessState(state.orders)
            is OrdersState.Error -> handleError(state.message)
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

    private fun handleSuccessState(orders: OrdersResponse) {
        hideLoading()
        binding.rvOrders.adapter = OrdersAdapter(orders)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    }

