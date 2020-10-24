package com.qualifications.network

import com.qualifications.model.Activity
import com.qualifications.model.Subject
import retrofit2.Call
import retrofit2.http.*

interface QualificationsAPI {
    @Headers("Content-Type: application/json")
    @GET("getSubjects")
    fun getSubjects() : Call<List<Subject>>

    @Headers("Content-Type: application/json")
    @POST("saveSubject")
    fun saveSubject(@Body subject: Subject): Call<Subject>

    @Headers("Content-Type: application/json")
    @POST("saveActivity")
    fun saveActivity(@Body activity: Activity): Call<Activity>

    @Headers("Content-Type: application/json")
    @PUT("updateSubject/{code}")
    fun updateSubject(@Path("code") subjectCode: String, @Body subject: Subject): Call<Subject>

    @Headers("Content-Type: application/json")
    @PUT("updateActivity/{id}")
    fun updateActivity(@Path("id") activityId: Int, @Body activity: Activity): Call<Activity>

    @Headers("Content-Type: application/json")
    @DELETE("deleteSubject/{code}")
    fun deleteSubject(@Path("code") code: String): Call<Subject>

    @Headers("Content-Type: application/json")
    @DELETE("deleteActivity/{id}")
    fun deleteActivity(@Path("id") id: Int): Call<Activity>
}