package com.example.projetosmarttools.Clientes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.projetosmarttools.Util.Loading.LoadingScreen
import com.example.projetosmarttools.R
import com.example.projetosmarttools.Util.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalhesDoCliente : AppCompatActivity() {

    lateinit var telefone: TextView
    lateinit var email: TextView
    lateinit var cpf: TextView
    lateinit var nome: TextView
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_do_cliente)

        LoadingScreen.displayLoadingWithText(this, "", false)

        telefone = findViewById(R.id.tv_valor_cli)
        email = findViewById(R.id.tv_descri_cli)
        cpf = findViewById(R.id.tv_cpf_cli)
        nome = findViewById(R.id.tv_data_cli)
        sessionManager = SessionManager(this)

        val idCliente:Int = intent.getIntExtra("idCliente", 0)
        println("Esse é o id desse cliente: ${idCliente}")

        val request = ClienteService.getAllClients().fetchClientById(
            number = idCliente,
            token = "Bearer ${sessionManager.fetchAuthToken()}"
        ).enqueue(object : Callback<DetalheClienteVO>{
            override fun onResponse(
                call: Call<DetalheClienteVO>,
                response: Response<DetalheClienteVO>
            ) {
                if (response.code() == 200) {
                    nome.setText(response.body()!!.nome)
                    email.setText(response.body()!!.email)
                    cpf.setText(response.body()!!.cpf)
                    telefone.setText(response.body()!!.telefone)
                    LoadingScreen.hideLoading()
                }
            }

            override fun onFailure(call: Call<DetalheClienteVO>, t: Throwable) {
                LoadingScreen.hideLoading()
            }

        })
    }

    fun cadastro(view: View) {
        finish()
    }
}