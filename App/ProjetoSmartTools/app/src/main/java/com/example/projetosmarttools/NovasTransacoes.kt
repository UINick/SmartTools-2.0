package com.example.projetosmarttools

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.projetosmarttools.CadastroExtrato.EntradaExtrato
import com.example.projetosmarttools.CadastroExtrato.SaidaExtrato
import com.example.projetosmarttools.Clientes.CadastroCliente.CadastroDoCliente

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

    }
    
    fun clickCliente(view: View) {
        val cliente = Intent(baseContext,CadastroDoCliente::class.java)
        startActivity(cliente)
    }
}