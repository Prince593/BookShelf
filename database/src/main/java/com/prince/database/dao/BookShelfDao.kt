package com.prince.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prince.database.models.BookEntity
import kotlinx.coroutines.flow.Flow

/**
 * Dao for bookshelf database operations
 * @see BookEntity for more details
 */
@Dao
interface BookShelfDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<BookEntity>)

    @Query("SELECT * FROM books ORDER BY publishedChapterDate DESC")
    fun getBooks(): Flow<List<BookEntity>>

    @Query("DELETE FROM books WHERE id NOT IN (:ids)")
    suspend fun deleteNotInList(ids: List<String>)

    @Query("UPDATE books SET isFavorite = :isFavourite WHERE id = :id")
    suspend fun updateBook(id: String, isFavourite: Boolean)

    @Query("DELETE FROM books")
    suspend fun deleteAll()
}