package com.qualifications.network

import com.qualifications.model.LoginRequest
import com.qualifications.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersService {
    private val usersApi = ServiceBuilder.buildService(UsersAPI::class.java)

    fun saveUser(user: User , apiCallback: ApiCallback<User>) {
        usersApi.saveUser(user).enqueue(
            object : Callback<User> {
                override fun onResponse(call: Call<User> , response: Response<User>) {
                    apiCallback.onSuccess(response.body())
                }

                override fun onFailure(call: Call<User> , t: Throwable) {
                    apiCallback.onFail(t)
                }

            }
        )
    }

    fun login(loginRequest: LoginRequest , apiCallback: ApiCallback<User>) {
        usersApi.login(loginRequest).enqueue(
            object : Callback<User> {
                override fun onResponse(call: Call<User> , response: Response<User>) {
                    apiCallback.onSuccess(response.body())
                }

                override fun onFailure(call: Call<User> , t: Throwable) {
                    apiCallback.onFail(t)
                }

            }
        )
    }
}