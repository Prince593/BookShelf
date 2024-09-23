package com.prince.network.module

import com.prince.network.api.BookShelfApiService
import com.prince.network.api.CountryApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class CountryApiModule {

    @Provides
    fun getCountryApi(retrofit: Retrofit): CountryApiService {
        return retrofit.create(CountryApiService::class.java)
    }
}