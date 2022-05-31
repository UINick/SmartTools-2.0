package com.example.projetosmarttools.Servicos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.projetosmarttools.R
import com.example.projetosmarttools.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlterarStatusActivity : AppCompatActivity() {

    var idServico:Int = 0

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alterar_status)

        sessionManager = SessionManager(this)
        idServico = intent.getIntExtra("idServico", 0)
    }

    fun clickFinalizado(view: View) {
        callService(statusEsc = "finalizado")
    }

    fun clickEmAndamento(view: View) {
        callService(statusEsc = "em_andamento")
    }

    fun clickPendente(view: View) {
        callService(statusEsc = "pendente")
    }

    fun callService(statusEsc: String) {

        println("opaaaaa ====> ${statusEsc}")
        println("idddddddd ====> ${idServico}")

        val statusEscolhido = StatusServicoVO(statusServico = statusEsc)

        val request = ServicoService.servico()
        request.alterarStatus(token = "Bearer ${sessionManager.fetchAuthToken()}", id = idServico, status = statusEscolhido)
            .enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.code() == 200) {
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Vish", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(baseContext, "Deu ruim =(", Toast.LENGTH_SHORT).show()
                }

            })
    }
}