package com.example.usersdir.domain.mapper

import com.example.usersdir.data.api.schema.UserSchema
import com.example.usersdir.data.cache.entity.UserEntity
import com.example.usersdir.domain.model.User
import javax.inject.Inject

class UserMapper @Inject constructor() {
    fun mapToUser(schema: UserSchema): User {
        return User(
            email = schema.email,
            gender = schema.gender,
            id = schema.id,
            name = schema.name,
            status = schema.status
        )
    }

    fun mapToUser(entity: UserEntity): User {
        return User(
            email = entity.email,
            gender = entity.gender,
            id = entity.id,
            name = entity.name,
            status = entity.status
        )
    }

    fun mapToEntity(user: User): UserEntity {
        return UserEntity(
            email = user.email,
            gender = user.gender,
            id = user.id,
            name = user.name,
            status = user.status
        )
    }
}
