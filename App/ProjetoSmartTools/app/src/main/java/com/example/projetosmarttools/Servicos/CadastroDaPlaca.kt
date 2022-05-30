package com.example.projetosmarttools.Servicos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.projetosmarttools.CadastroExtrato.SaidaExtrato
import com.example.projetosmarttools.CadastroVeiculo.CadastroVeiculo
import com.example.projetosmarttools.Clientes.CadastroCliente.CadastroClienteService
import com.example.projetosmarttools.R

class CadastroDaPlaca : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_da_placa)
    }

    fun continueService(view: View) {
        val servico = Intent(baseContext, DetalheVeiculoServico::class.java)
        startActivity(servico)
    }

    fun cadastrarCarro(view: View) {
        val veiculo = Intent(baseContext, CadastroVeiculo::class.java)
        startActivity(veiculo)
    }

}