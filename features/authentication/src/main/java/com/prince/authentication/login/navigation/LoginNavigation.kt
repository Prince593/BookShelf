package com.prince.authentication.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.prince.authentication.login.LoginScreen

/**
 * This file contains the navigation logic for the login screen
 * It contains, route, navigation actions and navigation graph
 */

const val LOGIN_ROUTE = "login_route"

/**
 * Navigate to login screen, it is extension function for NavController
 * @param navOptions: navigation options
 */
fun NavController.navigateToLogin(navOptions: NavOptions? = null) =
    navigate(LOGIN_ROUTE, navOptions)

/**
 * Navigation graph for login screen
 * @param onClickSignup: action to be performed on signup click
 * @param onLoginIn: action to be performed on login click
 */
fun NavGraphBuilder.loginScreen(
    onClickSignup: () -> Unit,
    onLoginIn: (userId: String) -> Unit
) {
    composable(route = LOGIN_ROUTE) {
        LoginScreen(onClickSignup = onClickSignup, onLoginIn = onLoginIn)
    }
}
