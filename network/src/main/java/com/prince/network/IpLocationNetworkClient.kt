package com.prince.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class IpLocationNetworkClient {

    companion object {
        private const val BASE_URL = "http://ip-api.com/"
        private var instance: Retrofit? = null
        fun getInstance(): Retrofit {
            return instance ?: kotlin.run {
                val instanceInternal = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                instance = instanceInternal
                instanceInternal
            }
        }
    }
}