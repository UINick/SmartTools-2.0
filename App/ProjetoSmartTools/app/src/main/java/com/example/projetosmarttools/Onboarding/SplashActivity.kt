package com.example.projetosmarttools.Onboarding

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.projetosmarttools.DashBoard
import com.example.projetosmarttools.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, DashBoard::class.java)
            startActivity(intent)
        }, 5000)



    }

}