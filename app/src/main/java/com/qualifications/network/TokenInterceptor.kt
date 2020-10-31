package com.qualifications.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val sessionManager = SessionManager(context)

        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization" , "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}