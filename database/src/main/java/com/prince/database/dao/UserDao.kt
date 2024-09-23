package com.prince.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prince.database.models.UserEntity

/**
 * Dao for user database operations
 * @see UserEntity for more details
 */

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    suspend fun getUser(email : String, password : String) : UserEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user : UserEntity)

    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun checkIfEmailExists(email: String) : List<UserEntity>
}