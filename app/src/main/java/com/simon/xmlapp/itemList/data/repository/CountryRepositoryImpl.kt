package com.simon.xmlapp.itemList.data.repository

import com.simon.xmlapp.itemList.data.remote.CountryApiClientProvider
import com.simon.xmlapp.itemList.domain.model.CountryModel
import com.simon.xmlapp.itemList.domain.repository.CountryRepository
import com.simon.xmlapp.util.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CountryRepositoryImpl: CountryRepository {
    override fun getAllCountries(): Flow<UiState<List<CountryModel>>> = flow {
        emit(UiState.LOADING)
        try {
            val countryApiClient = CountryApiClientProvider.apiClient
            val response = countryApiClient.getAllCountries()
            if(response.isSuccessful) {
                val body = response.body()
                if(body == null) {
                    emit(UiState.ERROR(Exception("Body is null or empty")))
                } else {
                    val countryModels = body.map {
                        return@map CountryModel(
                            name = it.name,
                            region = it.region,
                            code = it.code,
                            capital = it.capital
                        )
                    }
                    emit(UiState.SUCCESS(data = countryModels))
                }
            } else {
                emit(UiState.ERROR(Exception("Response was not successful")))
            }
        } catch(e: Exception) {
            emit(UiState.ERROR(e))
        }
    }
}