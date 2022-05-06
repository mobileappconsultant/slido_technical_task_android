package com.example.usersdir.domain.usecase

import com.example.usersdir.data.api.request.UserRequest
import com.example.usersdir.data.repository.UserRepository
import com.example.usersdir.domain.mapper.UserMapper
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) {
    suspend fun execute(
        email: String,
        gender: String,
        name: String,
        status: String
    ) {
        val request = UserRequest(
            email = email,
            gender = gender,
            name = name,
            status = status
        )
        val user = userRepository.createUser(request)

        userRepository.insertUserToDb(userMapper.mapToEntity(user))
    }
}
