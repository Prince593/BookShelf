package com.prince.network.api

import com.prince.models.books.Book
import com.prince.models.country.Country
import com.prince.models.ip.IpLocation
import retrofit2.Response
import retrofit2.http.GET

interface IpLocationApiService {

    @GET("json")
    suspend fun getCurrentIpDetails(): Response<IpLocation?>
}