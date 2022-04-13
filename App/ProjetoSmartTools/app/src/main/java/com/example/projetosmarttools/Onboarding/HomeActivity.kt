package com.example.projetosmarttools.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.projetosmarttools.Login.LoginDoMecanico
import com.example.projetosmarttools.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun olaMecanico(v: View) {
        val telaLoginMec = Intent(this, LoginDoMecanico::class.java)
        startActivity(telaLoginMec)
    }

    fun olaCliente(v: View) {
        Toast.makeText(this, "Logando como Cliente", Toast.LENGTH_LONG).show()
    }
}