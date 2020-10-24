package com.qualifications.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.qualifications.model.Activity
import com.qualifications.model.Subject
import com.qualifications.network.ApiCallback
import com.qualifications.network.QualificationsService

class SubjectViewModel : ViewModel() {
    private val  qualificationsService = QualificationsService()
    var subjects: MutableLiveData<List<Subject>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun saveSubject(subject: Subject, apiCallback: ApiCallback<Subject>) {
        qualificationsService.saveSubject(subject, apiCallback)
    }

    fun saveActivity(activity: Activity, apiCallback: ApiCallback<Activity>) {
        qualificationsService.saveActivity(activity, apiCallback)
    }

    fun updateActivity(activity: Activity, apiCallback: ApiCallback<Activity>) {
        qualificationsService.updateActivity(activity, apiCallback)
    }

    fun updateSubject(subject: Subject, apiCallback: ApiCallback<Subject>) {
        qualificationsService.updateSubject(subject, apiCallback)
    }

    fun deleteActivity(activityCode: Int, apiCallback: ApiCallback<Activity>) {
        qualificationsService.deleteActivity(activityCode, apiCallback)
    }

    fun deleteSubject(subject: Subject, apiCallback: ApiCallback<Subject>) {
        qualificationsService.deleteSubject(subject.code, apiCallback)
    }

    private fun getSubjects() {
        qualificationsService.getSubjects(object: ApiCallback<List<Subject>> {
            override fun onSuccess(result: List<Subject>?) {
                subjects.postValue(result)
                isLoading.value = true
            }

            override fun onFail(exception: Throwable) {
                isLoading.value = true
            }
        })
    }

    fun refresh() {
        getSubjects()
    }
}