package com.anz.users_data.repository

import com.anz.common.result.Result
import com.anz.network.extension.safeApiCall
import com.anz.users_data.datasource.remote.UserApiService
import com.anz.users_data.mapper.toDomain
import com.anz.users_domain.model.User
import com.anz.users_domain.repository.UserRepository
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
