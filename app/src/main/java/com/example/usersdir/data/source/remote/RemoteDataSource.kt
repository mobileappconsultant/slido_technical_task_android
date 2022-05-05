package com.example.usersdir.data.source.remote

import com.example.usersdir.data.api.request.UserRequest
import com.example.usersdir.data.api.schema.UserSchema

interface RemoteDataSource {
    suspend fun getUsers(): List<UserSchema>
    suspend fun createUser(userRequest: UserRequest): UserSchema
    suspend fun deleteUser(id: Int)
}
