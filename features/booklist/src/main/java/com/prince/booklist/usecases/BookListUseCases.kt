package com.prince.booklist.usecases

import com.prince.data.bookshelf.repository.BookShelfRepositoryImpl
import com.prince.models.books.Book
import com.prince.utils.state.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookListUseCases @Inject constructor(private val repository: BookShelfRepositoryImpl) {
    fun getBookList(noCacheData : Boolean) : Flow<DataState<List<Book>?>> = repository.getBooks(noCacheData) // Get book list from repository
    fun getBookListFromNetwork() : Flow<DataState<List<Book>?>> = repository.getBooksFromNetwork() // Get book list from network using repository
    suspend fun updateFavourite(id: String, isFavourite: Boolean) = repository.updateFavourite(id, isFavourite) // Update favourite status of book using repository
    suspend fun clearData() = repository.clearData() // Clear data from repository
}