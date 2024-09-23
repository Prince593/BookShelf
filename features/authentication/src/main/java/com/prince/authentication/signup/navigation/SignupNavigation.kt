package com.prince.authentication.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.prince.authentication.signup.SignupScreen

/**
 * This file contains the navigation logic for the signup screen
 * It contains, route, navigation actions and navigation graph
 * @property SIGNUP_ROUTE: signup route
 */
const val SIGNUP_ROUTE = "signup_route"

/**
 * Navigate to signup screen, it is extension function for NavController
 * @param navOptions: navigation options
 */

fun NavController.navigateToSignup(navOptions: NavOptions? = null) = navigate(SIGNUP_ROUTE, navOptions)

/**
 * Navigation graph for signup screen
 * @param onClickLogin: action to be performed on login click
 * @param onSignUp: action to be performed on signup click
 */

fun NavGraphBuilder.signupScreen(
    onClickLogin: () -> Unit,
    onSignUp: () -> Unit
) {
    composable(route = SIGNUP_ROUTE) {
        SignupScreen(onClickLogin = onClickLogin, onSingUp = onSignUp)
    }
}
