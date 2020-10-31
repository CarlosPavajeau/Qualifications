package com.qualifications.network

import android.content.Context
import com.qualifications.model.LoginRequest
import com.qualifications.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersService(private val context: Context) {
    private val usersApi = ServiceBuilder.buildService(UsersAPI::class.java , context)

    fun saveUser(user: User , apiCallback: ApiCallback<User>) {
        usersApi.saveUser(user).enqueue(
            object : Callback<User> {
                override fun onResponse(call: Call<User> , response: Response<User>) {
                    apiCallback.onResponse(response)
                }

                override fun onFailure(call: Call<User> , t: Throwable) {
                    apiCallback.onFailure(t)
                }

            }
        )
    }

    fun login(loginRequest: LoginRequest , apiCallback: ApiCallback<User>) {
        usersApi.login(loginRequest).enqueue(
            object : Callback<User> {
                override fun onResponse(call: Call<User> , response: Response<User>) {
                    apiCallback.onResponse(response)
                }

                override fun onFailure(call: Call<User> , t: Throwable) {
                    apiCallback.onFailure(t)
                }

            }
        )
    }
}