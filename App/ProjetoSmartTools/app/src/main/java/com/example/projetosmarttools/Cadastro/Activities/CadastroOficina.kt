package com.example.projetosmarttools.Cadastro.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.projetosmarttools.BottomSheetErrorFragment
import com.example.projetosmarttools.Cadastro.Service.Oficina.CadastroOficina
import com.example.projetosmarttools.Cadastro.Service.Oficina.CadastroOficina.Companion.criar
import com.example.projetosmarttools.Cadastro.Service.Oficina.CadastroOficinaVO
import com.example.projetosmarttools.Login.Activities.LoginDoMecanico
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
        val openModal = BottomSheetErrorFragment()
        val postOficina = CadastroOficina.criar().post(novaOficina)
        val confirmPassword = Intent(this, LoginDoMecanico:: class.java)

        postOficina.enqueue(object : Callback<Void> {

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() == 201) {
                    startActivity(confirmPassword)
                } else {
                    openModal.setUp(supportFragmentManager, title = "Não cadastrado", btnTitle = "Okay")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                openModal.setUp(supportFragmentManager, title = "Erro na API", btnTitle = "Okay")
            }

        })
    }
}