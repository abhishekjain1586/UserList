package com.anz.users_domain.usecase

import com.anz.common.result.Result
import com.anz.users_domain.model.User
import com.anz.users_domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<List<User>> {
        return userRepository.getUsers()
    }
}
