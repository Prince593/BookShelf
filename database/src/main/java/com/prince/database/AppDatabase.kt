package com.prince.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prince.database.dao.BookShelfDao
import com.prince.database.dao.UserDao
import com.prince.database.models.BookEntity
import com.prince.database.models.UserEntity

/**
 * Database class for creating database instance and providing dao instances
 */
@Database(entities = [UserEntity::class, BookEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UserDao
    abstract fun bookShelfDao(): BookShelfDao
}