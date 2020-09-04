package com.dicoding.github.lastsubmission.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dicoding.github.lastsubmission.R
import com.dicoding.github.lastsubmission.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        moveIntent()
    }


    private fun moveIntent() {
        Handler().postDelayed({
            Intent(this@SplashScreenActivity, MainActivity::class.java).also {
                startActivity(it)
            }
        }, 3000)
    }
}