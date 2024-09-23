package com.prince.network.api

import com.prince.models.books.Book
import com.prince.models.country.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountryApiService {

    @GET("b/IU1K")
    suspend fun getCountryList(): Response<List<Country>?>
}