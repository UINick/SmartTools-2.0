package com.example.projetosmarttools.CadastroExtrato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.projetosmarttools.R

class SaidaExtrato : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saida_extrato)
    }
    fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_entrada, container, false)
    }
    fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onViewCreated(view, savedInstanceState)
        val spinner: Spinner = view.findViewById(R.id.sp_categoria_saida)
        ArrayAdapter.createFromResource(
            view.context,
            R.array.categoria_saida,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }
}