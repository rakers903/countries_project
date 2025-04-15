package com.simon.xmlapp.itemList.domain.repository

import com.simon.xmlapp.itemList.domain.model.CountryModel
import com.simon.xmlapp.util.UiState
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    fun getAllCountries(): Flow<UiState<List<CountryModel>>>
}