package com.qualifications.view.ui.activities

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.qualifications.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavigationUI.setupWithNavController(bottom_menu, Navigation.findNavController(this,
            R.id.main_content
        ))

        requestPermissions(arrayOf(Manifest.permission.INTERNET),0)
    }
}