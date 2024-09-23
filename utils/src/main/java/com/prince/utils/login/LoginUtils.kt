package com.prince.utils.login

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import com.prince.utils.pref.MyPref
import java.util.regex.Pattern

/**
 * Check if the email is valid or not
 * @param email Email to be validated
 * @return true if email is valid, false otherwise
 */
fun isValidEmail(email : String) : Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}


/**
 * Check if the password is valid or not based on the following rules:
 * Password should be at least 8 characters,
 * including at least one number,
 * one special character -> !@#\$%^&*(),
 * one lowercase letter,
 * and one uppercase letter.
 * @param password Password to be validated
 * @return true if password is valid, false otherwise
 */
fun isValidPassword(password: String): Boolean {
    val passwordPattern = "^(?=.*[0-9])(?=.*[!@#\$%^&*(),])(?=.*[a-z])(?=.*[A-Z]).{8,}$" // Got this regex from internet
    val pattern = Pattern.compile(passwordPattern)
    val matcher = pattern.matcher(password)
    return matcher.matches()
}

fun isUserLoggedIn(context : Context) : Boolean {
    // Get Logged In User Id
    return !MyPref.getLoggedInUserId(context).isNullOrEmpty()
}