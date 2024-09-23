package com.prince.utils.state


sealed class DataState<out T> {
    data class Success<out T>(val response: T) : DataState<T>()
    data class Error(val errorMessage: String) : DataState<Nothing>()
    data object Loading : DataState<Nothing>()
}