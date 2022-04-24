package com.example.projetosmarttools.Login.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.projetosmarttools.Cadastro.Activities.CadastroOficina
import com.example.projetosmarttools.Login.Service.LoginMecanico
import com.example.projetosmarttools.Login.Service.LoginMecanicoVO
import com.example.projetosmarttools.R
import com.example.projetosmarttools.Service.ApiClient
import com.example.projetosmarttools.Service.LoginRequest
import com.example.projetosmarttools.Service.LoginResponse
import com.example.projetosmarttools.Service.SessionManager
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class LoginDoMecanico : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    lateinit var tiEmailLogin: TextInputLayout
    lateinit var tiSenhaLogin: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_do_mecanico)

        tiEmailLogin = findViewById(R.id.ti_email_l)
        tiSenhaLogin = findViewById(R.id.ti_senha_l)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)
    }

    fun goToCadastroMecanico(v:View) {
        val cadastroOficina = Intent(this, CadastroOficina::class.java)
        startActivity(cadastroOficina)

    }

    fun entrar(v:View) {
        val newLoginRequest = LoginMecanicoVO(
            tiEmailLogin.editText?.text.toString(),
            tiSenhaLogin.editText?.text.toString()
        )
        val request = LoginMecanico.efetuar().post(newLoginRequest)
        request.enqueue(object : retrofit2.Callback<Void> {

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() == 200) {
                    Toast.makeText(baseContext, "Logado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "CODE: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("ERRO AQUI: ${t.message}")
                Toast.makeText(baseContext, "Erro: ${t.stackTrace}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}