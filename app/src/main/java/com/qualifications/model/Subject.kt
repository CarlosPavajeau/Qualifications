package com.qualifications.model

class Subject(val code: String, val name: String) {
    val qualifications: ArrayList<Qualification> = ArrayList(3)

    val definitive: Float
    get() {
        var accumulate = 0.0f
        qualifications.forEach { accumulate += it.total }
        return accumulate / qualifications.count()
    }

    /**
     * Add a qualification to the subject with specific cort
     * */
    fun addQualification(qualification: Qualification) {
        if (qualification.cort > 3 || qualification.cort <= 0)
            return

        qualifications[qualification.cort] = qualification
    }
}