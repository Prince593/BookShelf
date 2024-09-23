package com.prince.booklist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.prince.booklist.components.BookList
import com.prince.booklist.components.BookListShimmerView
import com.prince.booklist.components.BookShelfToolbar
import com.prince.booklist.components.ErrorView
import com.prince.booklist.components.LogoutConfirmationDialog
import com.prince.booklist.viewmodel.BookListUiState
import com.prince.booklist.viewmodel.BookListViewModel
import com.prince.utils.booklist.getYear
import com.prince.utils.login.isUserLoggedIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(
    modifier: Modifier = Modifier,
    bookListViewModel: BookListViewModel = hiltViewModel(),
    onLogout: () -> Unit = {}
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val firstVisibleItem by remember {
        derivedStateOf { listState.firstVisibleItemIndex }
    }

    // Check if user is logged in or not and logout if not
    if (!isUserLoggedIn(context)) {
        onLogout()
        return
    }

    val bookListState by bookListViewModel.bookListUiState.collectAsStateWithLifecycle()
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var tabs by remember {
        mutableStateOf(listOf<String>())
    }
    var showLogoutDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        // Fetch the book list
        bookListViewModel.getBookList()
    }

    Scaffold(
        topBar = {
            BookShelfToolbar(
                onLogoutClick = {
                    showLogoutDialog = true
                },
                tabs = tabs,
                selectedTabIndex = selectedTabIndex
            ) {
                // Scroll to the selected year when user clicks on the tab
                coroutineScope.launch {
                    val selectedYear = tabs[it] // Year
                    val scrollToIndex =
                        bookListViewModel.getIndexPosition(selectedYear) // Index of the year in list
                    if (scrollToIndex != -1) {
                        listState.scrollToItem(scrollToIndex) // Scroll to the year
                    }
                }
            }
        }
    ) { padding ->

        val isRefreshing by bookListViewModel.isRefreshing.collectAsStateWithLifecycle()
        val pullRefreshState = rememberPullToRefreshState()
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            state = pullRefreshState,
            onRefresh = {
                bookListViewModel.getBookList(noCacheData = true)
            },
            modifier = Modifier
                .background(Color.White)
                .padding(padding)
                .fillMaxSize()
                .then(modifier)
        ) {
            when (bookListState) {
                is BookListUiState.Error -> {
                    ErrorView((bookListState as BookListUiState.Error).errorMessage) // Show error view, when hard refresh or no data available (API error)
                }

                BookListUiState.Loading -> {
                    Column {
                        repeat(10) {
                            BookListShimmerView() // Show shimmer view while loading
                        }
                    }
                }

                is BookListUiState.Success -> {
                    val bookData = (bookListState as? BookListUiState.Success)
                    val bookList = bookData?.bookList.orEmpty()
                    tabs = bookData?.tabs.orEmpty()

                    LaunchedEffect(firstVisibleItem) {
                        // Select tab based on firstVisibleItem (Book Year)
                        selectedTabIndex = tabs.indexOf(bookList[firstVisibleItem]
                            .publishedChapterDate?.let { timestamp ->
                                getYear(timestamp)
                            })
                    }

                    // Book list view
                    BookList(bookList = bookList, state = listState) { id, isFavourite ->
                        // Update favourite status of book
                        bookListViewModel.updateFavourite(id, isFavourite)
                    }

                }
            }
        }
    }

    if (showLogoutDialog) {
        // Show logout confirmation dialog if user clicks on logout
        LogoutConfirmationDialog(onLogout = {
            bookListViewModel.clearData()
            onLogout()
            showLogoutDialog = false
        }, onDismissRequest = {
            showLogoutDialog = false
        })
    }
}