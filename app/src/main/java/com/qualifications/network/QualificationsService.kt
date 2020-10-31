package com.qualifications.network

import android.content.Context
import com.qualifications.model.Activity
import com.qualifications.model.Subject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QualificationsService(private val context: Context) {
    private val retrofit = ServiceBuilder.buildService(QualificationsAPI::class.java , context)


    fun getSubjects(apiCallback: ApiCallback<List<Subject>>) {
        val sessionManager = SessionManager(context)
        sessionManager.fetchUserId()?.let {
            retrofit.getSubjects(it).enqueue(
                object : Callback<List<Subject>> {
                    override fun onFailure(call: Call<List<Subject>> , t: Throwable) {
                        apiCallback.onFailure(t)
                    }

                    override fun onResponse(
                        call: Call<List<Subject>> ,
                        response: Response<List<Subject>>
                    ) {
                        apiCallback.onResponse(response)
                    }
                }
            )
        }
    }

    fun saveSubject(subject: Subject , apiCallback: ApiCallback<Subject>) {
        retrofit.saveSubject(subject).enqueue(
            object : Callback<Subject> {
                override fun onFailure(call: Call<Subject> , t: Throwable) {
                    apiCallback.onFailure(t)
                }

                override fun onResponse(call: Call<Subject> , response: Response<Subject>) {
                    apiCallback.onResponse(response)
                }
            }
        )
    }

    fun saveActivity(activity: Activity , apiCallback: ApiCallback<Activity>) {
        retrofit.saveActivity(activity).enqueue(
            object : Callback<Activity> {
                override fun onFailure(call: Call<Activity> , t: Throwable) {
                    apiCallback.onFailure(t)
                }

                override fun onResponse(call: Call<Activity> , response: Response<Activity>) {
                    apiCallback.onResponse(response)
                }
            }
        )
    }

    fun updateSubject(subject: Subject , apiCallback: ApiCallback<Subject>) {
        retrofit.updateSubject(subject.code , subject).enqueue(
            object : Callback<Subject> {
                override fun onFailure(call: Call<Subject> , t: Throwable) {
                    apiCallback.onFailure(t)
                }

                override fun onResponse(call: Call<Subject> , response: Response<Subject>) {
                    apiCallback.onResponse(response)
                }
            }
        )
    }

    fun updateActivity(activity: Activity , apiCallback: ApiCallback<Activity>) {
        retrofit.updateActivity(activity.id , activity).enqueue(
            object : Callback<Activity> {
                override fun onFailure(call: Call<Activity> , t: Throwable) {
                    apiCallback.onFailure(t)
                }

                override fun onResponse(call: Call<Activity> , response: Response<Activity>) {
                    apiCallback.onResponse(response)
                }
            }
        )
    }

    fun deleteSubject(subjectCode: String , apiCallback: ApiCallback<Subject>) {
        retrofit.deleteSubject(subjectCode).enqueue(
            object : Callback<Subject> {
                override fun onFailure(call: Call<Subject> , t: Throwable) {
                    apiCallback.onFailure(t)
                }

                override fun onResponse(call: Call<Subject> , response: Response<Subject>) {
                    apiCallback.onResponse(response)
                }
            }
        )
    }

    fun deleteActivity(activityId: Int , apiCallback: ApiCallback<Activity>) {
        retrofit.deleteActivity(activityId).enqueue(
            object : Callback<Activity> {
                override fun onFailure(call: Call<Activity> , t: Throwable) {
                    apiCallback.onFailure(t)
                }

                override fun onResponse(call: Call<Activity> , response: Response<Activity>) {
                    apiCallback.onResponse(response)
                }
            }
        )
    }
}