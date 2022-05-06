package com.example.usersdir.domain.usecase

import com.example.usersdir.data.api.request.UserRequest
import com.example.usersdir.data.source.local.LocalDataSource
import com.example.usersdir.data.source.remote.RemoteDataSource
import com.example.usersdir.domain.mapper.UserMapper
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val userMapper: UserMapper
) {
    suspend fun execute(
        email: String,
        gender: String,
        name: String,
        status: String
    ) {
        val request = UserRequest(
            email = email,
            gender = gender,
            name = name,
            status = status
        )
        val user = userMapper.mapToUser(remoteDataSource.createUser(request))

        localDataSource.insertUsers(listOf(userMapper.mapToEntity(user)))
    }
}
