package com.example.projetosmarttools.CadastroExtrato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.projetosmarttools.Fragment.Extrato.ExtratoCadastro
import com.example.projetosmarttools.Fragment.Extrato.ExtratoService
import com.example.projetosmarttools.Fragment.Loading.LoadingScreen
import com.example.projetosmarttools.R
import com.example.projetosmarttools.SessionManager
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SaidaExtrato : AppCompatActivity() {

    lateinit var spinner: Spinner
    lateinit var categoriaSelecionada: String
    lateinit var valorSaida: TextInputLayout
    lateinit var descricaoSaida: TextInputLayout
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saida_extrato)

        spinner = findViewById(R.id.sp_categoria_saida)
        valorSaida = findViewById(R.id.ti_valor)
        descricaoSaida = findViewById(R.id.ti_descricao)
        sessionManager = SessionManager(this)

        val options = arrayOf("Funcionario", "Contas", "Peças", "Outros")

        spinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                categoriaSelecionada = options.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                categoriaSelecionada = "Selecione uma opção"
            }

        }
    }

    fun cadastrarSaida(view: View) {

        LoadingScreen.displayLoadingWithText(this, "", false)
        val newOutcomeRequest = ExtratoCadastro(
            categoria = categoriaSelecionada,
            descricao = descricaoSaida.editText?.text.toString(),
            valor = valorSaida.editText?.text.toString().toDouble()
        )
        val request = ExtratoService.extrato().postDespesa(
            token = "Bearer ${sessionManager.fetchAuthToken()}",
            novaSaida = newOutcomeRequest
        ).enqueue(object : Callback<Void> {

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                LoadingScreen.hideLoading()
                finish()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(view.context, "Deu ruim =(", Toast.LENGTH_SHORT).show()
            }

        })
    }
}
