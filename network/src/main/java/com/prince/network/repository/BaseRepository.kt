package com.prince.network.repository

import com.prince.utils.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T> apiCall(call: suspend () -> Response<T?>): Flow<DataState<T?>> = flow {
    try {
        emit(DataState.Loading)
        val response = call()
        if (response.isSuccessful) {
            emit(DataState.Success(response.body()))
        } else {
            emit(DataState.Error(response.errorBody()?.string() ?: "Unknown error"))
        }

    } catch (e: Exception) {
        emit(DataState.Error(e.message ?: "Unknown error"))
    }
}