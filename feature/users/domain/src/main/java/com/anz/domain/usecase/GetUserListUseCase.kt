package com.anz.domain.usecase

import com.anz.common.result.Result
import com.anz.domain.model.User
import com.anz.domain.repository.UserRepository
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<List<User>> {
        return userRepository.getUsers()
    }
}
