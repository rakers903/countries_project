package com.simon.xmlapp.itemList.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CountryApiClientProvider {
    private val BASE_URL = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"
    val apiClient = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CountryApi::class.java)
}