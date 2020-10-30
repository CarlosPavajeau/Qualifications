package com.qualifications.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Subject(@SerializedName("code") var code: String , @SerializedName("name") var name: String) :
    Serializable {
    @SerializedName("qualifications")
    val qualifications: ArrayList<Qualification> = ArrayList()

    @SerializedName("definitive")
    var definitive: Float = 0.0F

    @SerializedName("userId")
    lateinit var userId: String
}