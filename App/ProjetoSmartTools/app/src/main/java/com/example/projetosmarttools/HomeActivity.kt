package com.example.projetosmarttools

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun olaMecanico(v: View) {
        Toast.makeText(this, "Logando como Mecânico", Toast.LENGTH_LONG).show()

    }

    fun olaCliente(v: View) {
        Toast.makeText(this, "Logando como Cliente", Toast.LENGTH_LONG).show()
    }
}