package com.example.usersdir.data.repository

import com.example.usersdir.data.api.request.UserRequest
import com.example.usersdir.data.cache.entity.UserEntity
import com.example.usersdir.data.source.local.LocalDataSource
import com.example.usersdir.data.source.remote.RemoteDataSource
import com.example.usersdir.domain.mapper.UserMapper
import com.example.usersdir.domain.model.User
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val mapper: UserMapper
) {
    suspend fun getUsersFromRemote(): List<User> {
        return remoteDataSource.getUsers().map {
            mapper.mapToUser(it)
        }
    }

    suspend fun deleteUserFromRemote(id: Int) {
        remoteDataSource.deleteUser(id)
    }

    suspend fun createUser(request: UserRequest) {
        remoteDataSource.createUser(request)
    }

    suspend fun insertUserToDb(userEntities: List<UserEntity>) {
        localDataSource.insertUsers(userEntities)
    }

    fun getUsersFromDb(): Flow<List<User>> {
        return localDataSource.getUsers().map {
            it.map { entity ->
                mapper.mapToUser(entity)
            }
        }
    }

    suspend fun deleteUserFromDb(id: Int) {
        localDataSource.deleteUser(id)
    }
}
