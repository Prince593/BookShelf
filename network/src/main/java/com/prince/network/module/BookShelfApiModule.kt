package com.prince.network.module

import com.prince.network.api.BookShelfApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class BookShelfApiModule {

    @Provides
    fun getBookShelfApi(retrofit: Retrofit): BookShelfApiService {
        return retrofit.create(BookShelfApiService::class.java)
    }
}