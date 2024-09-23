package com.prince.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val email : String,
    val password : String,
    val country : String
)
