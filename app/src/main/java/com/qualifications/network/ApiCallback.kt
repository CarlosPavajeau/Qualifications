package com.qualifications.network

interface ApiCallback<T> {
    fun onSuccess(result: T?)
    fun onFail(exception: Throwable)
}