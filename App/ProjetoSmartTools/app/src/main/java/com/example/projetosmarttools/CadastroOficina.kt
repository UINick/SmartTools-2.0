package com.example.projetosmarttools

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CadastroOficina : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_oficina)
    }

    fun goToConfirmPassword(v:View) {
        val confirmPassword = Intent(this, CadastroPassword:: class.java)
        startActivity(confirmPassword)
    }
}