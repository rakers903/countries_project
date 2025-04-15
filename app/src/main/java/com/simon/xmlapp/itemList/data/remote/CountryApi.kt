package com.simon.xmlapp.itemList.data.remote

import com.simon.xmlapp.itemList.data.remote.dtos.Countries
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {
    @GET("countries.json")
    suspend fun getAllCountries(): Response<Countries>
}