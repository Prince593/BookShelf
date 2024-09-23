package com.prince.data.authentication.repository.repository

import com.prince.models.user.User
import com.prince.data.authentication.repository.helper.asDomain
import com.prince.data.authentication.repository.helper.asEntity
import com.prince.database.dao.UserDao
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {

    override suspend fun getUser(email: String, password: String): User? {
        // Get user by email and password from database
        return userDao.getUser(email, password)?.asDomain()
    }

    override suspend fun insertUser(user: User) {
        // Insert user into database
        return userDao.insertUser(user.asEntity())
    }

    override suspend fun checkIfEmailExists(email: String): List<User> {
        // Check if email exists in database, if exists return the user
        return userDao.checkIfEmailExists(email).map { it.asDomain() }
    }
}