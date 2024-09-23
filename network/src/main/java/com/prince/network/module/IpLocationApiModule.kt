package com.prince.network.module

import com.prince.network.IpLocationNetworkClient
import com.prince.network.api.IpLocationApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class IpLocationApiModule {

    @Provides
    fun getIpLocationApi(): IpLocationApiService {
        return IpLocationNetworkClient.getInstance().create(IpLocationApiService::class.java)
    }
}