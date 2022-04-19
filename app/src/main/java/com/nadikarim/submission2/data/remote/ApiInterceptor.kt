package com.nadikarim.submission2.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor(private var token: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}