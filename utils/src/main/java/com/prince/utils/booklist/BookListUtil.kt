package com.prince.utils.booklist

import com.prince.models.books.Book
import java.util.Calendar

fun prepareTabs(bookList: List<Book>): List<String> {
    return mutableListOf<String>().apply {
        bookList.forEach {
            it.publishedChapterDate?.let { timestamp ->
                if(timestamp > 0) {
                    val year = getYear(timestamp)
                    if(!contains(year)) {
                        add(year)
                    }
                }
            }
        }
    }
}

fun getYear(publishedChapterDate: Long): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = publishedChapterDate * 1000
    return calendar.get(Calendar.YEAR).toString()
}