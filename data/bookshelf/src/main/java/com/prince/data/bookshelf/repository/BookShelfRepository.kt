package com.prince.data.bookshelf.repository

import com.prince.models.books.Book
import com.prince.utils.state.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Repository to interact with bookshelf data
 * @see BookShelfRepositoryImpl for implementation
 * @see Book for more details
 * @see DataState for state management
 */
interface BookShelfRepository {
    fun getBooks(noCacheData : Boolean): Flow<DataState<List<Book>?>> // Get books from database
    fun getBooksFromNetwork(): Flow<DataState<List<Book>?>> // Get books from network
    suspend fun updateFavourite(id: String, isFavourite: Boolean) // Update favourite status of book in database
    suspend fun clearData() // Delete all data from database
}