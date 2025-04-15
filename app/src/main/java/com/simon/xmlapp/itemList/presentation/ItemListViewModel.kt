package com.simon.xmlapp.itemList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simon.xmlapp.itemList.domain.model.CountryModel
import com.simon.xmlapp.itemList.domain.repository.CountryRepository
import com.simon.xmlapp.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ItemListViewModel(
    private val countryRepository: CountryRepository
): ViewModel() {
    private var countryHolder: List<CountryModel> = emptyList()
    private var _countryState: MutableStateFlow<UiState<List<CountryModel>>> = MutableStateFlow(UiState.LOADING)
    val countryState: StateFlow<UiState<List<CountryModel>>> = _countryState.asStateFlow()

    init {
        viewModelScope.launch {
            countryRepository.getAllCountries().collect { countries ->
                if(countries is UiState.SUCCESS) {
                   countryHolder = countries.data
                }
                _countryState.value = countries
            }
        }
    }
}