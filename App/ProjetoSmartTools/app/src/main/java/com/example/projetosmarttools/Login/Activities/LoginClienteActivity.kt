package com.example.projetosmarttools.Login.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.projetosmarttools.CadastroVeiculo.CadastroVeiculo
import com.example.projetosmarttools.CadastroVeiculo.VeiculoService
import com.example.projetosmarttools.CadastroVeiculo.VeiculoVO
import com.example.projetosmarttools.Fragment.Loading.LoadingScreen
import com.example.projetosmarttools.Fragment.Modal.BottomSheetErrorFragment
import com.example.projetosmarttools.R
import com.example.projetosmarttools.Servicos.DetalheVeiculoServico
import com.example.projetosmarttools.SessionManager
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginClienteActivity : AppCompatActivity() {

    lateinit var placa: TextInputLayout
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_cliente)

        sessionManager = SessionManager(this)
        placa = findViewById(R.id.ti_placa_login)
    }

    fun entrar(view: View) {

        val openModal = BottomSheetErrorFragment()
        LoadingScreen.displayLoadingWithText(this, "", false)

        val request = VeiculoService.veiculo().fetchVeiculoByPlaca(
            token = "Bearer ${sessionManager.fetchAuthToken()}",
            placa = placa.editText?.text.toString()
        ).enqueue(object : Callback<VeiculoVO> {
            override fun onResponse(call: Call<VeiculoVO>, response: Response<VeiculoVO>) {
                LoadingScreen.hideLoading()
                openModal.setUp(supportFragmentManager, title = "Toppp", btnTitle = "Ok, entendi")
//                val servico = Intent(baseContext, DetalheVeiculoServico::class.java)
//                servico.putExtra("idVeiculo", response.body()!!.idVeiculo)
//                println("ID PASSADO ===> ${response.body()!!.idVeiculo}")
//                startActivity(servico)
            }

            override fun onFailure(call: Call<VeiculoVO>, t: Throwable) {
                LoadingScreen.hideLoading()
                openModal.setUp(supportFragmentManager, title = "O seu mecânico ainda não cadastrou seu veículo =(", btnTitle = "Ok, entendi")

            }


        })
    }


}