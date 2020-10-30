package com.qualifications.model

import com.google.gson.annotations.SerializedName

class LoginRequest(
    @SerializedName("usernameOrEmail") val usernameOrEmail: String ,
    @SerializedName("password") val password: String
)