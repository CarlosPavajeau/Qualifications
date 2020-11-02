package com.qualifications.view.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qualifications.R
import com.qualifications.network.SessionManager

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val sessionManager = SessionManager(this)
        val token = sessionManager.fetchAuthToken()
        if (token != null) {
            if (token.isNotEmpty() && token.isNotBlank()) {
                val intent = Intent(this , MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}