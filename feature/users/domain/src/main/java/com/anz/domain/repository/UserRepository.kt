package com.anz.domain.repository

import com.anz.common.result.Result
import com.anz.domain.model.User

interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
    suspend fun getUserById(userId: Int): Result<User>
}
