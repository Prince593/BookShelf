package com.prince.booklist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prince.booklist.usecases.BookListUseCases
import com.prince.models.books.Book
import com.prince.utils.booklist.getYear
import com.prince.utils.booklist.prepareTabs
import com.prince.utils.di.IoDispatcher
import com.prince.utils.state.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val useCases: BookListUseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _bookListUiState = MutableStateFlow<BookListUiState>(BookListUiState.Loading)
    val bookListUiState = _bookListUiState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    /**
     * Get the list of books from the database
     * @param noCacheData: Boolean - If true, fetches the data from the network
     */
    fun getBookList(noCacheData: Boolean = false) {
        viewModelScope.launch(dispatcher) {
            if (noCacheData) {
                _isRefreshing.value = true
            }
            useCases.getBookList(noCacheData).collect {
                _bookListUiState.value = when (it) {
                    is DataState.Error -> {
                        _isRefreshing.value = false
                        BookListUiState.Error(it.errorMessage)
                    }

                    is DataState.Loading -> BookListUiState.Loading
                    is DataState.Success -> {
                        delay(1500) // Intentional Delay for Shimmer
                        _isRefreshing.value = false
                        BookListUiState.Success(
                            it.response.orEmpty(),
                            prepareTabs(it.response.orEmpty())
                        )
                    }
                }
            }
        }
    }

    /**
     * Get the index position of the selected year
     */
    fun getIndexPosition(selectedYear: String): Int {
        if (_bookListUiState.value is BookListUiState.Success) {
            val bookList = (_bookListUiState.value as BookListUiState.Success).bookList

            // Find the index of the first item with the selected year
            return bookList.indexOfFirst {
                getYear(it.publishedChapterDate ?: 0) == selectedYear
            }

        }
        return -1
    }

    /**
     * Update the favourite status of the book
     */
    fun updateFavourite(id: String, isFavourite: Boolean) {
        viewModelScope.launch(dispatcher) {
            useCases.updateFavourite(id, isFavourite)
        }
    }

    /**
     * Clear the data from the database
     */
    fun clearData() {
        viewModelScope.launch {
            useCases.clearData()
        }
    }
}


sealed class BookListUiState {
    data object Loading : BookListUiState()
    data class Success(val bookList: List<Book>, val tabs: List<String>) : BookListUiState()
    data class Error(val errorMessage: String) : BookListUiState()
}