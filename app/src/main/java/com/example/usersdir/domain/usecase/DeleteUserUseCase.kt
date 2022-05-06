package com.example.usersdir.domain.usecase

import com.example.usersdir.data.source.local.LocalDataSource
import com.example.usersdir.data.source.remote.RemoteDataSource
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    suspend fun execute(id: Int) {
        remoteDataSource.deleteUser(id)
        localDataSource.deleteUser(id)
    }
}
