package com.example.usersdir.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.usersdir.data.cache.dao.UserDao
import com.example.usersdir.data.cache.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
