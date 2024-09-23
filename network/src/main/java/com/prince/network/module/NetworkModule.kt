package com.prince.network.module

import com.prince.network.IpLocationNetworkClient
import com.prince.network.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun getNetworkInstance() : Retrofit {
        return NetworkClient.getInstance()
    }
}