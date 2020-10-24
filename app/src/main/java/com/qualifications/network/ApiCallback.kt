package com.qualifications.network

import java.lang.Exception

interface ApiCallback<T> {
    fun onSuccess(result: T?)
    fun onFail(exception: Throwable)
}