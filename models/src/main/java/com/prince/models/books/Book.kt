package com.prince.models.books

import kotlin.math.roundToInt

data class Book(
    var id: String,
    var image: String? = null,
    var score: Double? = null,
    var popularity: Int? = null,
    var title: String? = null,
    var publishedChapterDate: Long? = null,
    var isFavorite: Boolean = false
)

fun Book.getRatingScore() : Double {
    // Score values are in range 0 to 100 but we need to show it in range 0 to 5
    // To make it in range 0 to 5, we will divide the score by 20
    val ratingScore = score?.div(20) ?: 0.0
    if(ratingScore >= 0 && ratingScore < 1) {
        // So if rating score is between 0 and 1, we will round it off
        return ratingScore.roundToInt().toDouble()
    }
   return ratingScore
}