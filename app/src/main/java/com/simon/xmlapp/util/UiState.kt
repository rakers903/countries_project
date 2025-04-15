package com.simon.xmlapp.util

sealed class UiState<out T> {
    data class SUCCESS<T>(val data: T): UiState<T>()
    data object LOADING: UiState<Nothing>()
    data class ERROR(val e: Throwable): UiState<Nothing>()
}