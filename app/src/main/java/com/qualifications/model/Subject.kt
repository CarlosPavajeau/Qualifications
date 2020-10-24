package com.qualifications.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Subject(@SerializedName("code") var code: String,@SerializedName("name") var name: String) : Serializable {
    @SerializedName("qualifications")
    val qualifications: ArrayList<Qualification> = ArrayList()

    @SerializedName("definitive")
    var definitive: Float = 0.0F

    fun addQualification(qualification: Qualification): Boolean {
        if (qualification.cort > 3 || qualification.cort <= 0)
            return false

        qualifications[qualification.cort - 1] = qualification
        return true
    }
}