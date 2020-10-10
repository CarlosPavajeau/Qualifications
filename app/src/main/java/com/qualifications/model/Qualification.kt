package com.qualifications.model

import androidx.annotation.FloatRange
import androidx.annotation.IntRange

class Qualification {
    var id: Int = 0

    @IntRange(from = 1, to = 3)
    var cort: Int = 1

    @FloatRange(from = 0.0, to = 1.0)
    var quizPercent: Float = 0.0f
    var quizValue: Float = 0.0f

    @FloatRange(from = 0.0, to = 1.0)
    var theoryPercent: Float = 0.0f
    var theoryValue: Float = 0.0f

    @FloatRange(from = 0.0, to = 1.0)
    var worksPercent: Float = 0.0f
    var worksValue: Float = 0.0f

    @FloatRange(from = 0.0, to = 1.0)
    var practicePercent: Float = 0.0f
    var practiceValue: Float = 0.0f

    val total: Float
    get() {
        return (quizPercent * quizValue) + (theoryPercent * theoryValue) + (worksPercent + worksValue) + (practicePercent * practiceValue)
    }

}