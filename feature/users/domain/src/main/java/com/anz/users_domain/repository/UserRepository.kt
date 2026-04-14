package com.anz.users_domain.repository

import com.anz.common.result.Result
import com.anz.users_domain.model.User

interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
    suspend fun getUserById(userId: Int): Result<User>
}
