package com.qualifications.network

import android.content.Context
import com.qualifications.model.LoginRequest
import com.qualifications.model.User

class UsersService(private val context: Context) {
    private val usersApi = ServiceBuilder.buildService(UsersAPI::class.java , context)

    fun saveUser(user: User , apiCallback: ApiCallback<User>) {
        usersApi.saveUser(user).enqueue(Callback(apiCallback))
    }

    fun login(loginRequest: LoginRequest , apiCallback: ApiCallback<User>) {
        usersApi.login(loginRequest).enqueue(Callback(apiCallback))
    }
}