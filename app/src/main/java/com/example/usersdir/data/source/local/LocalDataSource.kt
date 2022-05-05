package com.example.usersdir.data.source.local

import com.example.usersdir.data.cache.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getUsers(): Flow<List<UserEntity>>
    suspend fun deleteUser(id: Int)
    suspend fun insertUsers(users: List<UserEntity>)
}
