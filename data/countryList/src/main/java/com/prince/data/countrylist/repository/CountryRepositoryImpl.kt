package com.prince.data.countrylist.repository

import com.prince.models.country.Country
import com.prince.network.api.CountryApiService
import com.prince.network.repository.apiCall
import com.prince.utils.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(private val apiService: CountryApiService) :
    CountryRepository {

    /**
     * Get country list from API
     */
    override fun getCountryList(): Flow<DataState<List<Country>?>> = flow {
        emit(DataState.Loading)
        apiCall {
            apiService.getCountryList()
        }.collect {
            emit(it)
        }
    }
}