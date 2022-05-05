package com.example.usersdir.data.source.local

import com.example.usersdir.data.cache.dao.UserDao
import com.example.usersdir.data.cache.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(private val userDao: UserDao) : LocalDataSource {
    override fun getUsers(): Flow<List<UserEntity>> = userDao.selectUsers()

    override suspend fun deleteUser(id: Int) = userDao.deleteUser(id)

    override suspend fun insertUsers(users: List<UserEntity>) = userDao.insertUsers(users)
}
