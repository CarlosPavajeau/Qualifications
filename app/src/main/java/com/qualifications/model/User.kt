package com.qualifications.model

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("username") val username: String ,
    @SerializedName("email") val email: String ,
    @SerializedName("password") val password: String
) {
    @SerializedName("token")
    lateinit var token: String

    @SerializedName("id")
    lateinit var id: String
}