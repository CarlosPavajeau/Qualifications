package com.qualifications.network

import com.qualifications.model.LoginRequest
import com.qualifications.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

private const val USERS_API_URL = "api/Users"

interface UsersAPI {
    @Headers("Content-Type: application/json")
    @POST(USERS_API_URL)
    fun saveUser(@Body user: User): Call<User>

    @Headers("Content-Type: application/json")
    @POST("$USERS_API_URL/Login")
    fun login(@Body loginRequest: LoginRequest): Call<User>
}