package com.prince.network.api

import com.prince.models.books.Book
import retrofit2.Response
import retrofit2.http.GET

interface BookShelfApiService {

    @GET("b/CNGI")
    suspend fun getBooks(): Response<List<Book>?>
}