package com.example.usersdir.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.usersdir.data.cache.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Query("DELETE FROM user_entity WHERE id = :id")
    suspend fun deleteUser(id: Int)

    @Query("DELETE FROM user_entity")
    suspend fun deleteAll()

    @Query("SELECT * FROM user_entity ORDER BY id DESC")
    fun selectUsers(): Flow<List<UserEntity>>
}
