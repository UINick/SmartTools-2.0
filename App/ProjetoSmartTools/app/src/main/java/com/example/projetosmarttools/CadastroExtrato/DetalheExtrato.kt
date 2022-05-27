package com.example.projetosmarttools.CadastroExtrato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.projetosmarttools.Fragment.Loading.LoadingScreen
import com.example.projetosmarttools.R
import com.example.projetosmarttools.SessionManager
import com.google.android.material.textfield.TextInputLayout

class DetalheExtrato : AppCompatActivity() {

    lateinit var descricaoExtrato: TextView
    lateinit var categoriaExtrato: TextView
    lateinit var dataExtrato: TextView
    lateinit var precoExtrato: TextView
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_extrato)

        //LoadingScreen.displayLoadingWithText(this, "", false)

        descricaoExtrato = findViewById(R.id.tv_descri_extr)
        categoriaExtrato = findViewById(R.id.tv_categ_extr)
        dataExtrato = findViewById(R.id.tv_data_extr)
        precoExtrato = findViewById(R.id.tv_preco_extr)
        sessionManager = SessionManager(this)

        val idExtrato:Int = intent.getIntExtra("idExtrato", 0)
        println("Esse é o id desse extrato: ${idExtrato}")


    }
}