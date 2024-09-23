package com.prince.data.iplocation.repository

import com.prince.models.ip.IpLocation
import com.prince.utils.state.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Repository to interact with ip location data
 */
internal interface IpLocationRepository {
    fun getIpLocation(): Flow<DataState<IpLocation?>> // Get ip location from database
}