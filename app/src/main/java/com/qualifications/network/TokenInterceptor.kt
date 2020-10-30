package com.qualifications.network

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val sessionManager = ServiceBuilder.context?.let { SessionManager(it) }
        sessionManager?.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization" , "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}