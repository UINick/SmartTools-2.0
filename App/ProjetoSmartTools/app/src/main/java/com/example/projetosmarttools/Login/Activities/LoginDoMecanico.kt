package com.example.projetosmarttools.Login.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.projetosmarttools.Cadastro.Activities.CadastroOficina
import com.example.projetosmarttools.R
import com.example.projetosmarttools.Service.ApiClient
import com.example.projetosmarttools.Service.LoginRequest
import com.example.projetosmarttools.Service.LoginResponse
import com.example.projetosmarttools.Service.SessionManager

class LoginDoMecanico : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_do_mecanico)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

//        apiClient.getApiService().login(LoginRequest(email = "s@sample.com", password = "mypassword"))
//            .enqueue(object : Callback<LoginResponse> {
//                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    // Error logging in
//                }
//                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                    val loginResponse = response.body()
//
//                    if (loginResponse?.statusCode == 200 && loginResponse.user != null) {
//                        sessionManager.saveAuthToken(loginResponse.authToken)
//                    } else {
//                        // Error logging in
//                    }
//                }
//            })
    }

    fun goToCadastroMecanico(v:View) {
        val cadastroOficina = Intent(this, CadastroOficina::class.java)
        startActivity(cadastroOficina)
    }
}