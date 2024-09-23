package com.prince.authentication.domain

import com.prince.data.authentication.repository.repository.UserRepositoryImpl
import com.prince.models.user.User
import javax.inject.Inject

class UserUseCases @Inject constructor(private val userRepositoryImpl: UserRepositoryImpl) {

    // Get user from database using repository
    suspend fun getUser(email: String, password: String) =
        userRepositoryImpl.getUser(email, password)

    // Insert user to database using repository
    suspend fun insertUser(email: String, password: String, country: String) =
        userRepositoryImpl.insertUser(User(email, password, country))

    // Check if email exists in database
    suspend fun checkIfEmailExists(email: String): Boolean {
        return userRepositoryImpl.checkIfEmailExists(email).isNotEmpty()
    }
}