package com.qualifications.model

import androidx.annotation.IntRange

private const val MAX_PERCENT = 1.0

class Qualification {
    var id: Int = 0

    @IntRange(from = 1, to = 3)
    var cort: Int = 1

    var activities: ArrayList<Activity> = ArrayList()

    private val totalPartial: Float
    get() {
        return activities.map { a -> a.percent * a.note }.reduce { acc, fl -> acc + fl }
    }

    val total: Float
    get() {
        return totalPartial * totalPercent
    }

    private val totalPercent: Float
    get() {
        return when (cort) {
            1, 2 -> 0.3F
            3 -> 0.4F
            else -> 0.0F
        }
    }

    private val totalActivitiesPercent: Float
    get() {
        return activities.map { a -> a.percent }.reduce { acc, fl -> acc + fl }
    }

    fun addActivity(activity: Activity): Boolean {
        if (totalActivitiesPercent + activity.percent > MAX_PERCENT)
            return false

        activities.add(activity)
        return true
    }
}