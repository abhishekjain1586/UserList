package com.anz.network.extension

import com.anz.common.result.Result
import retrofit2.Response
import java.io.IOException

suspend fun <T, R> safeApiCall(
    apiCall: suspend () -> Response<T>,
    transform: (T) -> R
): Result<R> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Result.Success(transform(body))
            } else {
                Result.Error("Response body is null")
            }
        } else {
            val errorMessage = when (response.code()) {
                400 -> "Bad request. Please try again."
                401, 403 -> "You are not authorized to view this content."
                404 -> "Resource not found."
                500, 502, 503 -> "The server is temporarily unavailable. Please try again later."
                else -> response.errorBody()?.string() ?: "An error occurred (HTTP ${response.code()})."
            }
            Result.Error(errorMessage)
        }
    } catch (e: IOException) {
        Result.Error("No internet connection. Please check your network and try again.")
    } catch (e: Exception) {
        Result.Error(e.message ?: "An unexpected error occurred.")
    }
}
