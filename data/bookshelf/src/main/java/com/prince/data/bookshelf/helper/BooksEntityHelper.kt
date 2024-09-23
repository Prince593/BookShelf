package com.prince.data.bookshelf.helper

import com.prince.database.models.BookEntity
import com.prince.models.books.Book

// Extension function to convert model to database entity model
internal fun Book.asEntity(): BookEntity {
    return BookEntity(
        id = id,
        image = image,
        score = score,
        popularity = popularity,
        title = title,
        publishedChapterDate = publishedChapterDate,
        isFavorite = isFavorite
    )
}

// Extension function to convert database entity model to model
internal fun BookEntity.asDomain(): Book {
    return Book(
        id = id,
        image = image,
        score = score,
        popularity = popularity,
        title = title,
        publishedChapterDate = publishedChapterDate,
        isFavorite = isFavorite
    )
}