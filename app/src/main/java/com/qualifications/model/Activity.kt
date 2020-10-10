package com.qualifications.model

import androidx.annotation.FloatRange
import java.io.Serializable

class Activity : Serializable {
    var id: Int = 0
    var name: String = ""

    @FloatRange(from = 0.0, to = 5.0)
    var note: Float = 0.0F
    @FloatRange(from = 0.0, to = 1.0)
    var percent: Float = 0.0F
}