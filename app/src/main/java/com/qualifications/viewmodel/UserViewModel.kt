package com.qualifications.viewmodel

import androidx.lifecycle.ViewModel
import com.qualifications.model.LoginRequest
import com.qualifications.model.User
import com.qualifications.network.ApiCallback
import com.qualifications.network.UsersService

class UserViewModel : ViewModel() {
    private val userService = UsersService()

    fun saveUser(user: User , apiCallback: ApiCallback<User>) {
        userService.saveUser(user , apiCallback)
    }

    fun login(loginRequest: LoginRequest , apiCallback: ApiCallback<User>) {
        userService.login(loginRequest , apiCallback)
    }
}