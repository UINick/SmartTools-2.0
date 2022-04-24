package com.example.projetosmarttools.Cadastro.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.projetosmarttools.Cadastro.Service.Oficina.CadastroOficina
import com.example.projetosmarttools.Cadastro.Service.Oficina.CadastroOficina.Companion.criar
import com.example.projetosmarttools.Cadastro.Service.Oficina.CadastroOficinaVO
import com.example.projetosmarttools.R
import com.example.projetosmarttools.Service.ApiClient

import com.example.projetosmarttools.Service.SessionManager
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
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_oficina)

        tiNome = findViewById(R.id.ti_nome)
        tiEmail = findViewById(R.id.ti_email)
        tiCnpj = findViewById(R.id.ti_cnpj)
        tiTelefone = findViewById(R.id.ti_telefone)
        tiSenha = findViewById(R.id.ti_senha)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)
    }

    fun goToConfirmPassword(v:View) {

        val novaOficina = CadastroOficinaVO(
            tiNome.editText?.text.toString(),
            tiEmail.editText?.text.toString(),
            tiSenha.editText?.text.toString(),
            tiCnpj.editText?.text.toString(),
            tiTelefone.editText?.text.toString(),
        )

        val postOficina = CadastroOficina.criar().post(novaOficina)

        postOficina.enqueue(object : Callback<Void> {

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() == 201) {
                    Toast.makeText(baseContext, "Criado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "Erro na API", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("ERRO AQUI: ${t.message}")
                Toast.makeText(baseContext, "Erro: ${t.stackTrace}", Toast.LENGTH_SHORT).show()
            }

        })
        val confirmPassword = Intent(this, CadastroPassword:: class.java)
        startActivity(confirmPassword)
    }
}