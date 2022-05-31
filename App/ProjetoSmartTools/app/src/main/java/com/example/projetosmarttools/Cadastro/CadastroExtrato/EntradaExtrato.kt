package com.example.projetosmarttools.Cadastro.CadastroExtrato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.projetosmarttools.Extrato.ExtratoCadastro
import com.example.projetosmarttools.Extrato.ExtratoService
import com.example.projetosmarttools.Util.Loading.LoadingScreen
import com.example.projetosmarttools.R
import com.example.projetosmarttools.Util.SessionManager
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EntradaExtrato : AppCompatActivity() {

    lateinit var spinner: Spinner
    lateinit var categoriaSelecionada: String
    lateinit var tiValor: TextInputLayout
    lateinit var tiDescricao: TextInputLayout
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrada_extrato)

        spinner = findViewById(R.id.sp_categoria_entrada)
        tiValor = findViewById(R.id.ti_valor_entrada)
        tiDescricao = findViewById(R.id.ti_descricao_entrada)
        sessionManager = SessionManager(this)

        val options = arrayOf("Manutenção", "Troca de óleo", "Funilaria", "Peças", "Pinturas")

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

    fun cadastrarEntrada(view: View) {

        LoadingScreen.displayLoadingWithText(this, "", false)

        val newIncomeRequest = ExtratoCadastro(
            categoria = categoriaSelecionada,
            descricao = tiDescricao.editText?.text.toString(),
            valor = tiValor.editText?.text.toString().toDouble()
        )

        val request = ExtratoService.extrato().postReceita(
            token = "Bearer ${sessionManager.fetchAuthToken()}",
            novaEntrada = newIncomeRequest
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
