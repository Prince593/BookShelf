package com.prince.booklist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.prince.booklist.BookListScreen

/**
 * This file contains the navigation logic for the book list screen
 * It contains, route, navigation actions and navigation graph
 */
const val BOOK_LIST_ROUTE = "book_list_route"

/**
 * Navigate to book list screen, it is extension function for NavController
 * @param navOptions: navigation options
 */
fun NavController.navigateBookList(navOptions : NavOptions? = null) = navigate(BOOK_LIST_ROUTE, navOptions)

/**
 * Navigation graph for book list screen
 * @param onLogout: action to be performed on logout click
 */

fun NavGraphBuilder.bookListScreen(onLogout : () -> Unit) {
    composable(route = BOOK_LIST_ROUTE) {
        BookListScreen(onLogout = onLogout)
    }
}