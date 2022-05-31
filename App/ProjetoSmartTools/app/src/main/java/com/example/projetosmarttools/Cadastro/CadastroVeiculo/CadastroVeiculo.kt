package com.example.projetosmarttools.Cadastro.CadastroVeiculo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.projetosmarttools.Util.Loading.LoadingScreen
import com.example.projetosmarttools.R
import com.example.projetosmarttools.Util.SessionManager
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroVeiculo : AppCompatActivity() {

    lateinit var cpf: TextInputLayout
    lateinit var nome: TextInputLayout
    lateinit var placa: TextInputLayout
    lateinit var montadora: TextInputLayout
    lateinit var modelo: TextInputLayout
    lateinit var tipoVeiculo: TextInputLayout

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_veiculo)

        sessionManager = SessionManager(this)

        cpf = findViewById(R.id.ti_cpf_veiculo)
        nome = findViewById(R.id.ti_nome_cliente_veiculo)
        placa = findViewById(R.id.ti_placa_veiculo)
        montadora = findViewById(R.id.ti_montadora_veiculo)
        modelo = findViewById(R.id.ti_modelo_veiculo)
        tipoVeiculo = findViewById(R.id.ti_tipo_veiculo_veiculo)

    }

    fun cadastrarVeiculo(view: View) {

        LoadingScreen.displayLoadingWithText(this, "", false)

        val newVeiculoRequest = VeiculoVO(
            cpfCliente = cpf.editText?.text.toString(),
            marcaVeiculo = montadora.editText?.text.toString(),
            modeloVeiculo = modelo.editText?.text.toString(),
            nomeCliente = nome.editText?.text.toString(),
            placaVeiculo = placa.editText?.text.toString(),
            tipoVeiculo = tipoVeiculo.editText?.text.toString()
        )

        println("Cpf ===> ${newVeiculoRequest}")

        val request = VeiculoService.veiculo().postNewVeiculo(
            token = "Bearer ${sessionManager.fetchAuthToken()}",
            novoVeiculo = newVeiculoRequest
        ).enqueue(object : Callback<Void>{

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() == 201) {
                    LoadingScreen.hideLoading()
                    finish()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                LoadingScreen.hideLoading()
                Toast.makeText(baseContext, "Deu ruim", Toast.LENGTH_SHORT).show()
            }

        })

    }
}