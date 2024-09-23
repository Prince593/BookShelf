package com.prince.booklist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.prince.booklist.R
import com.prince.models.books.Book
import com.prince.models.books.getRatingScore
import com.prince.utils.booklist.getYear
import java.util.Locale

@Composable
fun BookListItemView(book: Book, onClickFavourite: (Boolean) -> Unit = {}) {
    ListItem(
        leadingContent = {
            // Book Image
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .aspectRatio(1.0f)
                    .clip(RoundedCornerShape(12.dp)),

                model = book.image.toString(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                placeholder = painterResource(id = R.drawable.image_placeholder)
            )
        },
        headlineContent = {
            Text(
                text = book.title.orEmpty(),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2
            )
        },
        supportingContent = {
            Column {
                // Book Score
                Row(
                    Modifier
                        .padding(vertical = 4.dp)
                        .background(
                            color = colorResource(id = android.R.color.holo_orange_dark).copy(
                                alpha = 0.1f
                            ), shape = RoundedCornerShape(8.dp)
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(16.dp),
                        imageVector = Icons.Rounded.Star,
                        contentDescription = null,
                        tint = colorResource(
                            id = android.R.color.holo_orange_dark
                        )
                    )
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = String.format(Locale.US, "%.1f", book.getRatingScore()),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = android.R.color.holo_orange_dark),
                    )
                }
                book.publishedChapterDate?.let {
                    Text(
                        stringResource(R.string.published_in, getYear(it)),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                }
            }
        },
        trailingContent = {
            // Favorite Icon
            IconButton(onClick = { onClickFavourite(!book.isFavorite) }) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = if (book.isFavorite) {
                        painterResource(id = R.drawable.ic_heart_flled)
                    } else {
                        painterResource(id = R.drawable.ic_heart_outlined)
                    },
                    tint = colorResource(id = R.color.light_red),
                    contentDescription = null
                )
            }

        },
        colors = ListItemDefaults.colors(
            containerColor = Color.White,
        )
    )
}


//Previews

@Preview(showBackground = true)
@Composable
fun BookListItemViewPreview() {
    BookListItemView(
        book = Book(
            id = "1",
            title = "Book Title",
            popularity = 4,
            publishedChapterDate = 1727004721,
            isFavorite = false
        )
    )
}

@Preview(showBackground = true)
@Composable
fun BookListItemViewFavouritePreview() {
    BookListItemView(
        book = Book(
            id = "1",
            title = "Book Title",
            score = 40.0,
            image = "https://via.placeholder.com/150",
            publishedChapterDate = 1727004721,
            isFavorite = true
        )
    )
}