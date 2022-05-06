package com.example.usersdir.domain.usecase

import com.example.usersdir.data.repository.UserRepository
import com.example.usersdir.domain.mapper.UserMapper
import com.example.usersdir.domain.model.User
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) {
    suspend fun execute(): Flow<List<User>> {
        return try {
            val users = userRepository.getUsersFromRemote()
            userRepository.insertUsersToDb(users.map { userMapper.mapToEntity(it) })
            userRepository.getUsersFromDb()
        } catch (exception: Exception) {
            if (userRepository.getUsersFromDb().first().isEmpty()) {
                throw exception
            }
            userRepository.getUsersFromDb()
        }
    }
}
