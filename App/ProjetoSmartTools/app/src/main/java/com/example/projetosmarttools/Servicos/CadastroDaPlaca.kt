package com.example.projetosmarttools.Servicos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.projetosmarttools.CadastroExtrato.SaidaExtrato
import com.example.projetosmarttools.CadastroVeiculo.CadastroVeiculo
import com.example.projetosmarttools.CadastroVeiculo.VeiculoService
import com.example.projetosmarttools.CadastroVeiculo.VeiculoVO
import com.example.projetosmarttools.Clientes.CadastroCliente.CadastroClienteService
import com.example.projetosmarttools.Fragment.Loading.LoadingScreen
import com.example.projetosmarttools.R
import com.example.projetosmarttools.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroDaPlaca : AppCompatActivity() {

    lateinit var search: EditText
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_da_placa)

        sessionManager = SessionManager(this)
        search = findViewById(R.id.search_placa_servico)
    }

    fun continueService(view: View) {

        LoadingScreen.displayLoadingWithText(this, "", false)

        val request = VeiculoService.veiculo().fetchVeiculoByPlaca(
            token = "Bearer ${sessionManager.fetchAuthToken()}",
            placa = search.text.toString()
        ).enqueue(object : Callback<VeiculoVO>{
            override fun onResponse(call: Call<VeiculoVO>, response: Response<VeiculoVO>) {
                    LoadingScreen.hideLoading()
                    val servico = Intent(baseContext, DetalheVeiculoServico::class.java)
                    servico.putExtra("idVeiculo", response.body()!!.idVeiculo)
                    println("ID PASSADO ===> ${response.body()!!.idVeiculo}")
                    startActivity(servico)
            }

            override fun onFailure(call: Call<VeiculoVO>, t: Throwable) {
                LoadingScreen.hideLoading()
                val veiculo = Intent(baseContext, CadastroVeiculo::class.java)
                startActivity(veiculo)
            }


        })
    }

    fun cadastrarCarro(view: View) {
        val veiculo = Intent(baseContext, CadastroVeiculo::class.java)
        startActivity(veiculo)
    }

}