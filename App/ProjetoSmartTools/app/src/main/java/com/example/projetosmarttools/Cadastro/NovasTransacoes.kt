package com.example.projetosmarttools.Cadastro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.projetosmarttools.Cadastro.CadastroExtrato.EntradaExtrato
import com.example.projetosmarttools.Cadastro.CadastroExtrato.SaidaExtrato
import com.example.projetosmarttools.Cadastro.CadastroCliente.CadastroDoCliente
import com.example.projetosmarttools.R
import com.example.projetosmarttools.Servicos.CadastroDaPlaca

class NovasTransacoes : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novas_transacoes)
    }

    fun clickEntrada(view: View) {
        val entrada = Intent(baseContext,EntradaExtrato::class.java)
        startActivity(entrada)
    }

    fun clickSaida(view: View) {
        val saida = Intent(baseContext,SaidaExtrato::class.java)
        startActivity(saida)
    }

    fun clickVeiculo(view: View) {
        val servico = Intent(baseContext,CadastroDaPlaca::class.java)
        startActivity(servico)
    }
    
    fun clickCliente(view: View) {
        val cliente = Intent(baseContext,CadastroDoCliente::class.java)
        startActivity(cliente)
    }
}