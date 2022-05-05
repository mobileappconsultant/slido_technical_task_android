package com.example.usersdir.di

import android.content.Context
import androidx.room.Room
import com.example.usersdir.data.cache.UserDatabase
import com.example.usersdir.data.cache.dao.UserDao
import com.example.usersdir.data.source.local.LocalDataSource
import com.example.usersdir.data.source.local.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, "user_database").build()
    }

    @Provides
    @Singleton
    fun providesDao(userDatabase: UserDatabase) = userDatabase.userDao()

    @Provides
    @Singleton
    fun provideLocalDatasource(userDao: UserDao): LocalDataSource {
        return LocalDataSourceImpl(userDao)
    }
}
