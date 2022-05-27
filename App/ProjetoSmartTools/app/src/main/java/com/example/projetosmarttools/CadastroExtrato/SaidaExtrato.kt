package com.example.projetosmarttools.CadastroExtrato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.projetosmarttools.R

class SaidaExtrato : AppCompatActivity() {

    lateinit var spinner: Spinner
    lateinit var categoria: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saida_extrato)

        spinner = findViewById(R.id.sp_categoria_saida)

        val options = arrayOf("Funcionario", "Contas", "Peças", "Outros")

        spinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                categoria = options.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                categoria = "Selecione uma opção"
            }

        }
    }
}
