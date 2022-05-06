package com.example.usersdir.domain.usecase

import com.example.usersdir.data.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(id: Int) {
        userRepository.deleteUserFromRemote(id)
        userRepository.deleteUserFromDb(id)
    }
}
