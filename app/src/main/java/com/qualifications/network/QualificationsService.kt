package com.qualifications.network

import android.content.Context
import com.qualifications.model.Activity
import com.qualifications.model.Subject

class QualificationsService(private val context: Context) {
    private val qualificationsApi =
        ServiceBuilder.buildService(QualificationsAPI::class.java , context)

    fun getSubjects(apiCallback: ApiCallback<List<Subject>>) {
        val sessionManager = SessionManager(context)
        sessionManager.fetchUserId()?.let {
            qualificationsApi.getSubjects(it).enqueue(Callback(apiCallback))
        }
    }

    fun saveSubject(subject: Subject , apiCallback: ApiCallback<Subject>) {
        qualificationsApi.saveSubject(subject).enqueue(Callback(apiCallback))
    }

    fun saveActivity(activity: Activity , apiCallback: ApiCallback<Activity>) {
        qualificationsApi.saveActivity(activity).enqueue(Callback(apiCallback))
    }

    fun updateSubject(subject: Subject , apiCallback: ApiCallback<Subject>) {
        qualificationsApi.updateSubject(subject.code , subject).enqueue(Callback(apiCallback))
    }

    fun updateActivity(activity: Activity , apiCallback: ApiCallback<Activity>) {
        qualificationsApi.updateActivity(activity.id , activity).enqueue(Callback(apiCallback))
    }

    fun deleteSubject(subjectCode: String , apiCallback: ApiCallback<Subject>) {
        qualificationsApi.deleteSubject(subjectCode).enqueue(Callback(apiCallback))
    }

    fun deleteActivity(activityId: Int , apiCallback: ApiCallback<Activity>) {
        qualificationsApi.deleteActivity(activityId).enqueue(Callback(apiCallback))
    }
}