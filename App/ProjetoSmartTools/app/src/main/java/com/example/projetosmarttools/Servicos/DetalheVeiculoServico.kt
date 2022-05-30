package com.example.projetosmarttools.Servicos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.projetosmarttools.CadastroVeiculo.VeiculoVO
import com.example.projetosmarttools.Fragment.Loading.LoadingScreen
import com.example.projetosmarttools.R
import com.example.projetosmarttools.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalheVeiculoServico : AppCompatActivity() {

    lateinit var tipoVeiculo: TextView
    lateinit var modelo: TextView
    lateinit var marca: TextView
    lateinit var placa: TextView

    lateinit var valorServico: EditText
    lateinit var descicaoServico: EditText

    private lateinit var sessionManager: SessionManager
    var idDoVeiculo: Int = 0
    var placaParaRegistrar: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_veiculo_servico)

        sessionManager = SessionManager(this)

        valorServico = findViewById(R.id.id_valor_servico)
        descicaoServico = findViewById(R.id.id_descricao_servico)

        modelo = findViewById(R.id.id_modeloo)
        marca = findViewById(R.id.id_montadoraa)
        tipoVeiculo = findViewById(R.id.id_veiculoo)
        placa = findViewById(R.id.id_placaa)

        val gettingIntentExtra = intent.getIntExtra("idVeiculo", 0)
        idDoVeiculo =  gettingIntentExtra

    }

    override fun onResume() {
        super.onResume()
        callVeiculoDetails()
    }

    fun callVeiculoDetails() {

        LoadingScreen.displayLoadingWithText(this, "", false)

        val request = ServicoService.servico().fetchVeiculoById(
            token = "Bearer ${sessionManager.fetchAuthToken()}",
            id = idDoVeiculo

        ).enqueue(object : Callback<VeiculoVO> {
            override fun onResponse(call: Call<VeiculoVO>, response: Response<VeiculoVO>) {
                if (response.code() == 200){
                    placaParaRegistrar = response.body()!!.placaVeiculo
                    modelo.text = response.body()!!.modeloVeiculo
                    placa.text = response.body()!!.placaVeiculo
                    marca.text = response.body()!!.marcaVeiculo
                    tipoVeiculo.text = response.body()!!.tipoVeiculo
                    LoadingScreen.hideLoading()
                }
            }

            override fun onFailure(call: Call<VeiculoVO>, t: Throwable) {
                LoadingScreen.hideLoading()
                Toast.makeText(baseContext, "Deu ruim", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun cadastrarServico(view: View) {

        LoadingScreen.displayLoadingWithText(this, "", false)

        val newService = DetalheServicoVO(
            categoria = "Outros",
            descricao = descicaoServico.text.toString(),
            placa = placaParaRegistrar,
            valorServico = valorServico.text.toString().toDouble()
        )

        val request = ServicoService.servico().postNewServico(
            token = "Bearer ${sessionManager.fetchAuthToken()}",
            novoServico = newService
        ).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                LoadingScreen.hideLoading()
                finish()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                LoadingScreen.hideLoading()
                Toast.makeText(baseContext, "Deu ruim", Toast.LENGTH_SHORT).show()
            }

        })

    }
}