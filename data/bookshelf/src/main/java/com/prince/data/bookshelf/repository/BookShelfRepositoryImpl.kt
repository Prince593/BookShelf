package com.prince.data.bookshelf.repository

import com.prince.data.bookshelf.helper.asDomain
import com.prince.data.bookshelf.helper.asEntity
import com.prince.database.dao.BookShelfDao
import com.prince.models.books.Book
import com.prince.network.api.BookShelfApiService
import com.prince.network.repository.apiCall
import com.prince.utils.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookShelfRepositoryImpl @Inject constructor(
    private val apiService: BookShelfApiService,
    private val bookShelfDao: BookShelfDao
) : BookShelfRepository {

    /**
     * Get the list of books from the database
     * @param noCacheData: Boolean - If true, fetches the data from the network
     */
    override fun getBooks(noCacheData: Boolean): Flow<DataState<List<Book>?>> = flow {
        emit(DataState.Loading)

        // Fetch the books from the network and cache it in the database
        val fetchAndCacheBooks: suspend () -> Unit = {
            getBooksFromNetwork().collect { networkState ->
                if (networkState is DataState.Success) {
                    val list = networkState.response.orEmpty()
                    val ids = list.map { it.id }

                    // Delete the books which are not in the list but are in the database and insert the new list
                    bookShelfDao.deleteNotInList(ids)
                    bookShelfDao.insertAll(list.map { it.asEntity() })
                } else {
                    emit(networkState)
                }
            }
        }

        if (noCacheData) {
            // Fetch the data from the network and cache it in the database if noCacheData is true
            fetchAndCacheBooks()
        }

        bookShelfDao.getBooks().collect { localData ->
            if (localData.isEmpty()) {
                // Fetch the data from the network and cache it in the database if the database is empty
                fetchAndCacheBooks()
            } else {
                emit(DataState.Success(localData.map { it.asDomain() }))
            }
        }
    }

    /**
     * Get the list of books from the network
     */
    override fun getBooksFromNetwork(): Flow<DataState<List<Book>?>> = flow {
        apiCall {
            apiService.getBooks()
        }.collect { dataState ->
            when (dataState) {
                is DataState.Error -> {
                    emit(DataState.Error(dataState.errorMessage))
                }

                DataState.Loading -> {
                    emit(DataState.Loading)
                }

                is DataState.Success -> {
                    val list = dataState.response.orEmpty()
                    emit(DataState.Success(list))
                }
            }
        }
    }

    /**
     * Update the favourite status of a book
     * @param id: String - Id of the book
     * @param isFavourite: Boolean - Favourite status of the book
     */
    override suspend fun updateFavourite(id: String, isFavourite: Boolean) {
        bookShelfDao.updateBook(id, isFavourite)
    }

    /**
     * Clear all data from the database
     */
    override suspend fun clearData() {
        bookShelfDao.deleteAll()
    }
}