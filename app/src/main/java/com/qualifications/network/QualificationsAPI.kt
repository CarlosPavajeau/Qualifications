package com.qualifications.network

import com.qualifications.model.Activity
import com.qualifications.model.Subject
import retrofit2.Call
import retrofit2.http.*

private const val QUALIFICATION_API_URL = "api/Qualifications"

interface QualificationsAPI {
    @GET("$QUALIFICATION_API_URL/getSubjects/{userId}")
    fun getSubjects(@Path("userId") userId: String): Call<List<Subject>>

    @POST("$QUALIFICATION_API_URL/saveSubject")
    fun saveSubject(@Body subject: Subject): Call<Subject>

    @POST("$QUALIFICATION_API_URL/saveActivity")
    fun saveActivity(@Body activity: Activity): Call<Activity>

    @PUT("$QUALIFICATION_API_URL/updateSubject/{code}")
    fun updateSubject(@Path("code") subjectCode: String , @Body subject: Subject): Call<Subject>

    @PUT("$QUALIFICATION_API_URL/updateActivity/{id}")
    fun updateActivity(@Path("id") activityId: Int , @Body activity: Activity): Call<Activity>

    @DELETE("$QUALIFICATION_API_URL/deleteSubject/{code}")
    fun deleteSubject(@Path("code") code: String): Call<Subject>

    @DELETE("$QUALIFICATION_API_URL/deleteActivity/{id}")
    fun deleteActivity(@Path("id") id: Int): Call<Activity>
}