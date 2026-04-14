package com.anz.data.repository

import com.anz.common.result.Result
import com.anz.network.extension.safeApiCall
import com.anz.data.datasource.remote.UserApiService
import com.anz.data.mapper.toDomain
import com.anz.domain.model.User
import com.anz.domain.repository.UserRepository
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val userApiService: UserApiService
) : UserRepository {

    override suspend fun getUsers(): Result<List<User>> {
        return safeApiCall(
            apiCall = { userApiService.getUsers() },
            transform = { list -> list.map { it.toDomain() } }
        )
    }

    override suspend fun getUserById(userId: Int): Result<User> {
        return safeApiCall(
            apiCall = { userApiService.getUserById(userId) },
            transform = { it.toDomain() }
        )
    }
}
