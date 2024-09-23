package com.prince.data.authentication.repository.repository

import com.prince.models.user.User

/**
 * Interface for User Repository to perform operations on User
 * @see User for more details
 * @see UserRepositoryImpl for implementation
 */
interface UserRepository {
    suspend fun getUser(email: String, password: String): User? // Get user by email and password
    suspend fun insertUser(user: User) // Insert user
    suspend fun checkIfEmailExists(email: String): List<User> // Check if email exists in database
}