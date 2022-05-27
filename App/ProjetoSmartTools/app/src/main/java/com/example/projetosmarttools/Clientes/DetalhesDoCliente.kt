package com.example.projetosmarttools.Clientes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.projetosmarttools.R

class DetalhesDoCliente : AppCompatActivity() {

    lateinit var valor: TextView
    lateinit var descricao: TextView
    lateinit var categoria: TextView
    lateinit var data: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_do_cliente)

        valor = findViewById(R.id.tv_valor_cli)
        descricao = findViewById(R.id.tv_descri_cli)
        categoria = findViewById(R.id.tv_categ_cli)
        data = findViewById(R.id.tv_data_cli)

        val idCliente:Int = intent.getIntExtra("idCliente", 0)
        println("Esse é o id desse cliente: ${idCliente}")
    }

    fun cadastro(view: View) {
        finish()
    }
}