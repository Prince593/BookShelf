package com.prince.data.iplocation.repository

import com.prince.models.ip.IpLocation
import com.prince.network.api.IpLocationApiService
import com.prince.network.repository.apiCall
import com.prince.utils.state.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IpLocationRepositoryImpl @Inject constructor(private val apiService: IpLocationApiService) :
    IpLocationRepository {

    /**
     * Get ip location from API service
     */
    override fun getIpLocation(): Flow<DataState<IpLocation?>> = apiCall {
        apiService.getCurrentIpDetails()
    }
}