package com.qualifications.network

import android.content.Context
import android.content.SharedPreferences
import com.qualifications.R

class SessionManager(context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name) , Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_ID = "user_id"
    }

    fun saveAuthToken(token: String , userId: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN , token)
        editor.putString(USER_ID , userId)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN , null)
    }

    fun fetchUserId(): String? {
        return prefs.getString(USER_ID , null)
    }

    fun deleteUser() {
        val editor = prefs.edit()
        editor.putString(USER_ID , null)
        editor.putString(USER_TOKEN , null)
        editor.apply()
    }
}