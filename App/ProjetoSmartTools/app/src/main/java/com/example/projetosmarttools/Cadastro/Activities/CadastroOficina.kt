package com.example.projetosmarttools.Cadastro.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.projetosmarttools.R

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