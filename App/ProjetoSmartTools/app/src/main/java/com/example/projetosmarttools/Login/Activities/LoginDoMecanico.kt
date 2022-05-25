package com.example.projetosmarttools.Login.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.projetosmarttools.Fragment.Modal.BottomSheetErrorFragment
import com.example.projetosmarttools.Cadastro.Activities.CadastroOficina
import com.example.projetosmarttools.Fragment.Modal.ItemBottomSheetClick
import com.example.projetosmarttools.Login.Service.LoginMecanico
import com.example.projetosmarttools.Login.Service.LoginMecanicoVO
import com.example.projetosmarttools.R
import com.example.projetosmarttools.Service.SessionManager
import com.google.android.material.textfield.TextInputLayout
import com.example.projetosmarttools.Fragment.Loading.LoadingScreen
import com.example.projetosmarttools.Login.Service.LogingResponse
import com.example.projetosmarttools.Main
import retrofit2.Call
import retrofit2.Response

class LoginDoMecanico : AppCompatActivity(), ItemBottomSheetClick {

    private lateinit var sessionManager: SessionManager

    lateinit var tiEmailLogin: TextInputLayout
    lateinit var tiSenhaLogin: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_do_mecanico)

        tiEmailLogin = findViewById(R.id.ti_email_l)
        tiSenhaLogin = findViewById(R.id.ti_senha_l)

        sessionManager = SessionManager(this)
    }

    fun goToCadastroMecanico(v:View) {
        val cadastroOficina = Intent(this, CadastroOficina::class.java)
        startActivity(cadastroOficina)

    }

    fun entrar(v:View) {

        LoadingScreen.displayLoadingWithText(this, "", false)

        val intent = Intent(this, Main::class.java)
        val openModal = BottomSheetErrorFragment()
        val newLoginRequest = LoginMecanicoVO(
            tiEmailLogin.editText?.text.toString(),
            tiSenhaLogin.editText?.text.toString()
        )
        val request = LoginMecanico.efetuar().post(newLoginRequest)
        request.enqueue(object : retrofit2.Callback<LogingResponse> {
            override fun onResponse(call: Call<LogingResponse>, response: Response<LogingResponse>) {
                if (response.code() == 200) {
                    println("TOKEN body = ${response.body()!!.token}")

                    sessionManager.saveAuthToken(response.body()!!.token)
                    LoadingScreen.hideLoading()
                    startActivity(intent)
                } else {
                    LoadingScreen.hideLoading()
                    openModal.setUp(supportFragmentManager, title = "Você não possui cadastro ainda", btnTitle = "Ok, entendi")
                }
            }

            override fun onFailure(call: Call<LogingResponse>, t: Throwable) {
                println("ERRO AQUI: ${t.message}")
                LoadingScreen.hideLoading()
                openModal.setUp(supportFragmentManager, title = "Erro na conexão, tente novamente mais tarde.", btnTitle = "Ok, entendi")
            }
        })
    }

    override fun onItemClick(item: String?) {
    }
}