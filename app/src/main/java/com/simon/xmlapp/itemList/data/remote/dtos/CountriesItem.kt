package com.simon.xmlapp.itemList.data.remote.dtos

data class CountriesItem(
    val capital: String,
    val code: String,
    val currency: Currency,
    val demonym: String,
    val flag: String,
    val language: Language,
    val name: String,
    val region: String
)