package com.qualifications.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.qualifications.data.QualificationDatabase
import com.qualifications.model.Activity

class ActivityViewModel(private val context: Context) : ViewModel() {
    private val qualificationDatabase = QualificationDatabase(context)
    var activities: MutableLiveData<List<Activity>> = MutableLiveData()
}