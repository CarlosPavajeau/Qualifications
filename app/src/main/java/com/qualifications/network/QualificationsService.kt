package com.qualifications.network

import com.qualifications.model.Activity
import com.qualifications.model.Subject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QualificationsService {
    private val retrofit = ServiceBuilder.buildService(QualificationsAPI::class.java)


    fun getSubjects(apiCallback: ApiCallback<List<Subject>>) {
        val sessionManager = ServiceBuilder.context?.let { SessionManager(it) }
        sessionManager?.fetchUserId()?.let {
            retrofit.getSubjects(it).enqueue(
                object : Callback<List<Subject>> {
                    override fun onFailure(call: Call<List<Subject>> , t: Throwable) {
                        apiCallback.onFail(t)
                    }

                    override fun onResponse(
                        call: Call<List<Subject>> ,
                        response: Response<List<Subject>>
                    ) {
                        apiCallback.onSuccess(response.body())
                    }
                }
            )
        }
    }

    fun saveSubject(subject: Subject , apiCallback: ApiCallback<Subject>) {
        retrofit.saveSubject(subject).enqueue(
            object : Callback<Subject> {
                override fun onFailure(call: Call<Subject> , t: Throwable) {
                    apiCallback.onFail(t)
                }

                override fun onResponse(call: Call<Subject> , response: Response<Subject>) {
                    val response = response.body()
                    apiCallback.onSuccess(response)
                }
            }
        )
    }

    fun saveActivity(activity: Activity , apiCallback: ApiCallback<Activity>) {
        retrofit.saveActivity(activity).enqueue(
            object : Callback<Activity> {
                override fun onFailure(call: Call<Activity> , t: Throwable) {
                    apiCallback.onFail(t)
                }

                override fun onResponse(call: Call<Activity> , response: Response<Activity>) {
                    val response = response.body()
                    apiCallback.onSuccess(response)
                }
            }
        )
    }

    fun updateSubject(subject: Subject , apiCallback: ApiCallback<Subject>) {
        retrofit.updateSubject(subject.code , subject).enqueue(
            object : Callback<Subject> {
                override fun onFailure(call: Call<Subject> , t: Throwable) {
                    apiCallback.onFail(t)
                }

                override fun onResponse(call: Call<Subject> , response: Response<Subject>) {
                    apiCallback.onSuccess(response.body())
                }
            }
        )
    }

    fun updateActivity(activity: Activity , apiCallback: ApiCallback<Activity>) {
        retrofit.updateActivity(activity.id , activity).enqueue(
            object : Callback<Activity> {
                override fun onFailure(call: Call<Activity> , t: Throwable) {
                    apiCallback.onFail(t)
                }

                override fun onResponse(call: Call<Activity> , response: Response<Activity>) {
                    apiCallback.onSuccess(response.body())
                }
            }
        )
    }

    fun deleteSubject(subjectCode: String , apiCallback: ApiCallback<Subject>) {
        retrofit.deleteSubject(subjectCode).enqueue(
            object : Callback<Subject> {
                override fun onFailure(call: Call<Subject> , t: Throwable) {
                    apiCallback.onFail(t)
                }

                override fun onResponse(call: Call<Subject> , response: Response<Subject>) {
                    apiCallback.onSuccess(response.body())
                }
            }
        )
    }

    fun deleteActivity(activityId: Int , apiCallback: ApiCallback<Activity>) {
        retrofit.deleteActivity(activityId).enqueue(
            object : Callback<Activity> {
                override fun onFailure(call: Call<Activity> , t: Throwable) {
                    apiCallback.onFail(t)
                }

                override fun onResponse(call: Call<Activity> , response: Response<Activity>) {
                    apiCallback.onSuccess(response.body())
                }
            }
        )
    }
}