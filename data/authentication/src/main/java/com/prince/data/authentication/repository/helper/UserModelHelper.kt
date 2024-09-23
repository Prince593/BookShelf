package com.prince.data.authentication.repository.helper

import com.prince.database.models.UserEntity
import com.prince.models.user.User

// Extension function to convert model to database entity model
internal fun User.asEntity() = UserEntity(
    email = email,
    password = password,
    country = country
)

// Extension function to convert database entity model to model
internal fun UserEntity.asDomain() = User(
    email = email,
    password = password,
    country = country
)