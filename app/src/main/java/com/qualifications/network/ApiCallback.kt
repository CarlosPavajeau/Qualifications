package com.qualifications.network

import retrofit2.Response

interface ApiCallback<T> {
    fun onResponse(result: Response<T>)
    fun onFailure(exception: Throwable)
}