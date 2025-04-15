package com.simon.xmlapp.itemList.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.simon.xmlapp.databinding.FragmentItemListBinding
import com.simon.xmlapp.itemList.data.repository.CountryRepositoryImpl
import com.simon.xmlapp.itemList.presentation.recyclerview.ItemListAdapter
import com.simon.xmlapp.util.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ItemListFragment: Fragment() {
    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!
    private val itemListViewModel: ItemListViewModel by viewModels {
        object: ViewModelProvider.Factory {
            override fun <T: ViewModel> create(modelClass: Class<T>): T {
                if(modelClass.isAssignableFrom(ItemListViewModel::class.java)) {
                    val countryRepository = CountryRepositoryImpl()
                    return ItemListViewModel(countryRepository = countryRepository) as T
                }
                throw IllegalStateException("Unknown ViewModel class")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.recyclerView
        val adapter = ItemListAdapter(data = emptyList())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        lifecycleScope.launch {
            itemListViewModel.countryState.collect {
                when(it) {
                    is UiState.ERROR -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.errorMessage.visibility = View.VISIBLE
                    }
                    UiState.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is UiState.SUCCESS -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        val (data) = it
                        adapter.updateData(data)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}