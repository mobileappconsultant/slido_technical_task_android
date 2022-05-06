package com.example.usersdir.domain.usecase

import com.example.usersdir.data.source.local.LocalDataSource
import com.example.usersdir.data.source.remote.RemoteDataSource
import com.example.usersdir.domain.mapper.UserMapper
import com.example.usersdir.domain.model.User
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class GetUsersUseCase @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val userMapper: UserMapper
) {
    suspend fun execute(): Flow<List<User>> {
        return try {
            val users = remoteDataSource.getUsers().map { userMapper.mapToUser(it) }
            localDataSource.insertUsers(users.map { userMapper.mapToEntity(it) })
            localDataSource.getUsers()
                .map { it.map { userEntity -> userMapper.mapToUser(userEntity) } }
        } catch (exception: Exception) {
            if (localDataSource.getUsers().first().isEmpty()) {
                throw exception
            }
            localDataSource.getUsers().map { it.map { entity -> userMapper.mapToUser(entity) } }
        }
    }
}
