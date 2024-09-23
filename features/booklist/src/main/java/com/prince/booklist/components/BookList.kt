package com.prince.booklist.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prince.models.books.Book

@Composable
fun BookList(
    modifier: Modifier = Modifier,
    state: LazyListState? = null,
    bookList: List<Book>,
    onUpdateFavourite: (String, Boolean) -> Unit,
) {
    val listState = state ?: rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .then(modifier),
        state = listState,
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(items = bookList, key = { it.id }) { book ->
            // Book list item view
            BookListItemView(book = book) {
                onUpdateFavourite(book.id, it) // Update favourite status of book
            }
        }
    }
}

// Previews
@Preview(showBackground = true)
@Composable
private fun BookListPreview() {
    BookList(
        bookList = listOf(
            Book(
                id = "1",
                title = "Book Title",
                publishedChapterDate = 0,
            )
        )
    ) { _, _ ->
    }
}