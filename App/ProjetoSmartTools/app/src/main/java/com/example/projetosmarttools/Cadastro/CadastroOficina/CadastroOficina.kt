package com.example.projetosmarttools.Cadastro.CadastroOficina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.projetosmarttools.Cadastro.CadastroOficina.Service.Oficina.CadastroOficina
import com.example.projetosmarttools.Cadastro.CadastroOficina.Service.Oficina.CadastroOficinaVO
import com.example.projetosmarttools.Util.Loading.LoadingScreen
import com.example.projetosmarttools.R

import com.example.projetosmarttools.Util.SessionManager
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroOficina : AppCompatActivity() {

    private lateinit var tiNome: TextInputLayout
    private lateinit var tiEmail: TextInputLayout
    private lateinit var tiCnpj: TextInputLayout
    private lateinit var tiTelefone: TextInputLayout
    private lateinit var tiSenha: TextInputLayout

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_oficina)

        tiNome = findViewById(R.id.ti_nome)
        tiEmail = findViewById(R.id.ti_email)
        tiCnpj = findViewById(R.id.ti_cnpj)
        tiTelefone = findViewById(R.id.ti_telefone)
        tiSenha = findViewById(R.id.ti_senha)

        sessionManager = SessionManager(this)
    }

    fun goToConfirmPassword(v:View) {

        LoadingScreen.displayLoadingWithText(this, "", false)

        val novaOficina = CadastroOficinaVO(
            tiCnpj.editText?.text.toString(),
            tiEmail.editText?.text.toString(),
            tiNome.editText?.text.toString(),
            tiSenha.editText?.text.toString(),
            tiTelefone.editText?.text.toString()
        )

        val postOficina = CadastroOficina.criar().post(novaOficina)

        postOficina.enqueue(object : Callback<Void> {

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() == 201) {
                    LoadingScreen.hideLoading()
                    finish()
                } else {
                    LoadingScreen.hideLoading()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("ESSE E O ERRO =========> ${t.message}")
                println("ESSE E O ERRO =========> ${t.stackTrace}")
                println("ESSE E O ERRO =========> ${t.localizedMessage}")
                println("ESSE E O ERRO =========> ${t.cause}")
                println("ESSE E O ERRO =========> ${t.suppressed}")
                LoadingScreen.hideLoading()
            }

        })
    }
}