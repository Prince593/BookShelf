package com.prince.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey var id: String,
    var image: String? = null,
    var score: Double? = null,
    var popularity: Int? = null,
    var title: String? = null,
    var publishedChapterDate: Long? = null,
    var isFavorite: Boolean = false
)
