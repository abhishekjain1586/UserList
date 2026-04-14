package com.anz.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.apply {
            addHeader("Content-Type", "application/json")
        }
        return chain.proceed(requestBuilder.build())
    }
}