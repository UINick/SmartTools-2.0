package com.example.projetosmarttools.Clientes.CadastroCliente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.projetosmarttools.Fragment.Loading.LoadingScreen
import com.example.projetosmarttools.Fragment.Modal.BottomSheetErrorFragment
import com.example.projetosmarttools.R
import com.example.projetosmarttools.SessionManager
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroDoCliente : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    lateinit var cpf : TextInputLayout
    lateinit var email : TextInputLayout
    lateinit var nome : TextInputLayout
    lateinit var telefone : TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_do_cliente)

        sessionManager = SessionManager(this)

        cpf = findViewById(R.id.ti_cpf_cadastro)
        email = findViewById(R.id.ti_email_cadastro)
        nome = findViewById(R.id.ti_nome_cadastro)
        telefone = findViewById(R.id.ti_telefone_cadastro)



    }

    fun goToConfirmPassword(view: View) {

        LoadingScreen.displayLoadingWithText(this, "", false)
        val openModal = BottomSheetErrorFragment()

        val newClientRequest = CadastroClienteVO(
            cpf.editText?.text.toString(),
            email.editText?.text.toString(),
            nome.editText?.text.toString(),
            telefone.editText?.text.toString()
        )

        val request = CadastroClienteService.criar().post(
            token = "Bearer ${sessionManager.fetchAuthToken()}",
            novoCliente = newClientRequest
        ).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                LoadingScreen.hideLoading()
                finish()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(view.context, "Deu ruim =(", Toast.LENGTH_SHORT).show()
            }

        })
    }


//    fun entrar(v:View) {
//
//        LoadingScreen.displayLoadingWithText(this, "", false)
//
//        val intent = Intent(this, Main::class.java)
//        val openModal = BottomSheetErrorFragment()
//        val newLoginRequest = LoginMecanicoVO(
//            tiEmailLogin.editText?.text.toString(),
//            tiSenhaLogin.editText?.text.toString()
//        )
//        val request = LoginMecanico.efetuar().post(newLoginRequest)
//        request.enqueue(object : retrofit2.Callback<LogingResponse> {
//            override fun onResponse(call: Call<LogingResponse>, response: Response<LogingResponse>) {
//                if (response.code() == 200) {
//                    println("TOKEN body = ${response.body()!!.token}")
//
//                    sessionManager.saveAuthToken(response.body()!!.token)
//                    LoadingScreen.hideLoading()
//                    startActivity(intent)
//                } else {
//                    LoadingScreen.hideLoading()
//                    openModal.setUp(supportFragmentManager, title = "Você não possui cadastro ainda", btnTitle = "Ok, entendi")
//                }
//            }
//
//            override fun onFailure(call: Call<LogingResponse>, t: Throwable) {
//                println("ERRO AQUI: ${t.message}")
//                LoadingScreen.hideLoading()
//                openModal.setUp(supportFragmentManager, title = "Erro na conexão, tente novamente mais tarde.", btnTitle = "Ok, entendi")
//            }
//        })
//    }
}