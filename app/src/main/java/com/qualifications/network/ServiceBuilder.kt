package com.qualifications.network

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    fun <T> buildService(service: Class<T> , context: Context): T {
        val client = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(TokenInterceptor(context))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.110/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(service)
    }
}