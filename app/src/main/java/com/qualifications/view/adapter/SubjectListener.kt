package com.qualifications.view.adapter

import com.qualifications.model.Subject

interface SubjectListener {
    fun onSubjectTap(subject: Subject, index: Int)
}