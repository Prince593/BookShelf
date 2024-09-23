package com.prince.data.countrylist.repository

import com.prince.models.country.Country
import com.prince.utils.state.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Interface for Country Repository to perform operations on Country
 * @see Country for more details
 * @see CountryRepositoryImpl for implementation
 */

internal interface CountryRepository {
    fun getCountryList(): Flow<DataState<List<Country>?>> // Get country list
}