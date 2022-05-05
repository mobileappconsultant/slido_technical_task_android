package com.example.usersdir.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_entity")
data class UserEntity(
    val email: String,
    val gender: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String
)
