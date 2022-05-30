package com.example.projetosmarttools.CadastroExtrato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.projetosmarttools.Fragment.Extrato.ExtratoDetalheVO
import com.example.projetosmarttools.Fragment.Extrato.ExtratoService
import com.example.projetosmarttools.Fragment.Extrato.ExtratoVO
import com.example.projetosmarttools.Fragment.Loading.LoadingScreen
import com.example.projetosmarttools.R
import com.example.projetosmarttools.SessionManager
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalheExtrato : AppCompatActivity() {

    lateinit var descricaoExtrato: TextView
    lateinit var categoriaExtrato: TextView
    lateinit var dataExtrato: TextView
    lateinit var precoExtrato: TextView
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_extrato)

        LoadingScreen.displayLoadingWithText(this, "", false)

        descricaoExtrato = findViewById(R.id.tv_descri_extr)
        categoriaExtrato = findViewById(R.id.tv_categ_extr)
        dataExtrato = findViewById(R.id.tv_data_extr)
        precoExtrato = findViewById(R.id.tv_preco_extr)
        sessionManager = SessionManager(this)

        val idExtrato:Int = intent.getIntExtra("idExtrato", 0)
        println("Esse é o id desse extrato: ${idExtrato}")

        val request = ExtratoService.extrato().fetchLancamentoWithId(
            idExtrato = idExtrato,
            token = "Bearer ${sessionManager.fetchAuthToken()}"
        ).enqueue(object : Callback<ExtratoDetalheVO>{
            override fun onResponse(
                call: Call<ExtratoDetalheVO>,
                response: Response<ExtratoDetalheVO>
            ) {
                if (response.code() == 200) {
                    descricaoExtrato.setText(response.body()!!.descricao)
                    categoriaExtrato.setText(response.body()!!.categoria.nome)
                    dataExtrato.setText(response.body()!!.dataRegistro)
                    precoExtrato.setText(response.body()!!.valor.toString())
                    LoadingScreen.hideLoading()
                }
            }

            override fun onFailure(call: Call<ExtratoDetalheVO>, t: Throwable) {
                LoadingScreen.hideLoading()
                println("Esse é o erro =====> ${t.stackTrace} ")
                println("Esse é o erro =====> ${t.message} ")
                println("Esse é o erro =====> ${t.cause} ")
                println("Esse é o erro =====> ${t.localizedMessage} ")
            }


        })

    }

    fun voltar(view: View) {
        finish()
    }
}