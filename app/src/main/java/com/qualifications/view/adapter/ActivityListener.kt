package com.qualifications.view.adapter

import com.qualifications.model.Activity

interface ActivityListener {
    fun onActivityEditButtonTap(activity: Activity , position: Int)
    fun onActivityDeleteButtonTap(activity: Activity, position: Int)
}