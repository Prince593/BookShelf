package com.prince.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.prince.authentication.login.navigation.LOGIN_ROUTE
import com.prince.authentication.login.navigation.loginScreen
import com.prince.authentication.login.navigation.navigateToLogin
import com.prince.authentication.signup.navigation.navigateToSignup
import com.prince.authentication.signup.navigation.signupScreen
import com.prince.booklist.navigation.BOOK_LIST_ROUTE
import com.prince.booklist.navigation.bookListScreen
import com.prince.booklist.navigation.navigateBookList
import com.prince.theme.BookShelfTheme
import com.prince.utils.pref.MyPref
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            BookShelfTheme {
                NavHost(
                    navController = navController,
                    startDestination = getStartDestination()
                ) {

                    // Lambda to navigate to login screen
                    val loginNavigator = {
                        MyPref.clear(this@MainActivity)
                        if (!navController.popBackStack(LOGIN_ROUTE, false)) {
                            navController.navigateToLogin(navOptions {
                                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                                launchSingleTop = true
                            })
                        }
                    }

                    loginScreen(onClickSignup = {
                        // Navigate to signup screen
                        navController.navigateToSignup()
                    }, onLoginIn = {
                        // Navigate to book list screen after login is successful
                        MyPref.setLoggedInUserId(this@MainActivity, it)
                        navController.navigateBookList(navOptions {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        })
                    })

                    signupScreen(
                        onClickLogin = loginNavigator,
                        onSignUp = loginNavigator
                    )

                    // Book List
                    bookListScreen(onLogout = {
                        MyPref.clear(this@MainActivity)
                        navController.navigate(LOGIN_ROUTE) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                            launchSingleTop = true
                        }
                    })

                }
            }
        }
    }

    private fun getStartDestination(): String {
        // To get the start destination for nav controller, if logged in / not
        return if (MyPref.getLoggedInUserId(this).isNullOrEmpty()) {
            LOGIN_ROUTE
        } else {
            BOOK_LIST_ROUTE
        }
    }
}