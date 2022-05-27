package com.example.projetosmarttools.Fragment.Extrato

import com.example.projetosmarttools.Clientes.CadastroCliente.CadastroClienteVO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ExtratoService {

    @GET("lancamentos")
    fun fetchLancamentos(@Header("Authorization") token: String): Call<List<ExtratoVO>>

    @POST("lancamentos/receitas")
    fun postReceita(@Header("Authorization") token: String, @Body novaEntrada: ExtratoCadastro): Call<Void>

    @POST("lancamentos/despesas")
    fun postDespesa(@Header("Authorization") token: String, @Body novaSaida: ExtratoCadastro): Call<Void>

    companion object {
        var BASE_URL = "https://apinewsmarttools.herokuapp.com/"
        fun extrato(): ExtratoService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ExtratoService::class.java)
        }

    }
}