package com.example.projetosmarttools.Login.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.projetosmarttools.CadastroVeiculo.CadastroVeiculo
import com.example.projetosmarttools.CadastroVeiculo.VeiculoService
import com.example.projetosmarttools.CadastroVeiculo.VeiculoVO
import com.example.projetosmarttools.Fragment.Extrato.ExtratoService
import com.example.projetosmarttools.Fragment.Loading.LoadingScreen
import com.example.projetosmarttools.Fragment.Modal.BottomSheetErrorFragment
import com.example.projetosmarttools.R
import com.example.projetosmarttools.Servicos.DetalheVeiculoServico
import com.example.projetosmarttools.Servicos.ServicoDetailsVO
import com.example.projetosmarttools.SessionManager
import com.example.projetosmarttools.StatusConserto
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginClienteActivity : AppCompatActivity() {

    lateinit var placaCliente: TextInputLayout
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_cliente)

        sessionManager = SessionManager(this)
        placaCliente = findViewById(R.id.ti_placa_login)
    }

    fun entrar(view: View) {

        val openModal = BottomSheetErrorFragment()
        LoadingScreen.displayLoadingWithText(this, "", false)

        val request = ExtratoService.extrato().buscarPlacaDoCliente(placa = placaCliente.editText?.text.toString())
            .enqueue(object : Callback<ServicoDetailsVO> {
                override fun onResponse(
                    call: Call<ServicoDetailsVO>,
                    response: Response<ServicoDetailsVO>
                ) {
                    LoadingScreen.hideLoading()
                    val statusConserto = Intent(view.context, StatusConserto::class.java)
                    statusConserto.putExtra("statusConserto", response.body()!!.statusServico)
                    startActivity(statusConserto)
                }

                override fun onFailure(call: Call<ServicoDetailsVO>, t: Throwable) {
                    LoadingScreen.hideLoading()
                    Toast.makeText(baseContext, "VISHHH", Toast.LENGTH_SHORT).show()
                }

            })
    }


}