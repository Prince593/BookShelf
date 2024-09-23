package com.prince.database.di

import android.content.Context
import androidx.room.Room
import com.prince.database.AppDatabase
import com.prince.database.dao.BookShelfDao
import com.prince.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Database module for providing database and dao instances
 * @see AppDatabase for more details
 * @see UserDao for more details
 * @see BookShelfDao for more details
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase {
        // Build the database instance using Room
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app-database",
        ).build()
    }

    @Provides
    fun providerUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.usersDao() // Return the UserDao instance
    }

    @Provides
    fun providesBookShelfDao(appDatabase: AppDatabase): BookShelfDao {
        return appDatabase.bookShelfDao() // Return the BookShelfDao instance
    }
}