package com.anz.domain.usecase

import com.anz.common.result.Result
import com.anz.domain.model.User
import com.anz.domain.repository.UserRepository
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: Int): Result<User> {
        return userRepository.getUserById(userId = userId)
    }
}
