package com.dicoding.github.lastsubmission.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.github.lastsubmission.R

class UserDetailActivity : AppCompatActivity() {
    companion object  {
        const val USERNAME_KEY = "username_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
    }
}