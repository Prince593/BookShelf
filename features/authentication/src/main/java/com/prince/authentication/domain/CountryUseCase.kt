package com.prince.authentication.domain

import com.prince.data.countrylist.repository.CountryRepositoryImpl
import com.prince.data.iplocation.repository.IpLocationRepositoryImpl
import com.prince.models.country.Country
import com.prince.utils.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use case to get country list and country from ip
 */
class CountryUseCase @Inject constructor(
    private val countryRepositoryImpl: CountryRepositoryImpl,
    private val ipLocationRepositoryImpl: IpLocationRepositoryImpl
) {

    /**
     * Get country list from repository and emit it as flow
     * @return Flow<List<Country>> country list
     * @see Country for more details
     */
    fun getCountryList(): Flow<List<Country>> = flow {
        countryRepositoryImpl.getCountryList().collect {
            when (it) {
                is DataState.Error -> emit(emptyList()) // Return empty list on error
                DataState.Loading -> {}
                is DataState.Success -> emit(
                    it.response
                        .orEmpty()
                        .sortedBy { country -> country.country } // Sort country list by country name
                )
            }
        }
    }

    /**
     * Get country name from ip and emit it as flow
     * @return Flow<String> country name
     */
    fun getCountryFromIp(): Flow<String> = flow {
        ipLocationRepositoryImpl.getIpLocation().collect {
            when (it) {
                is DataState.Error -> emit("") // Return empty string on error
                DataState.Loading -> {}
                is DataState.Success -> emit(it.response?.country.orEmpty()) // Return country name from ip response
            }
        }
    }
}