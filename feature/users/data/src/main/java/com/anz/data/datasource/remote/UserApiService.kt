package com.anz.data.datasource.remote

import com.anz.data.model.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<UserDto>>

    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId") userId: Int): Response<UserDto>
}