package com.example.usersdir.data.source.remote

import com.example.usersdir.data.api.UsersApi
import com.example.usersdir.data.api.request.UserRequest
import com.example.usersdir.data.api.schema.UserSchema

class RemoteDataSourceImpl(private val api: UsersApi) : RemoteDataSource {
    override suspend fun getUsers(): List<UserSchema> = api.getUsers()

    override suspend fun createUser(userRequest: UserRequest): UserSchema = api.createRequest(userRequest)

    override suspend fun deleteUser(id: Int) = api.deleteUser(id)
}
