package com.example.projetosmarttools

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoginDoMecanico : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_do_mecanico)
    }

    fun goToCadastroMecanico(v:View) {
        val cadastroOficina = Intent(this, CadastroOficina::class.java)
        startActivity(cadastroOficina)
    }
}