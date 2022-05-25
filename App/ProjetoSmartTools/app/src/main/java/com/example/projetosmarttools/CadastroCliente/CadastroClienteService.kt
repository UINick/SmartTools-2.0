package com.example.projetosmarttools.CadastroCliente

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CadastroClienteService {
    @POST("clientes")
    fun post(@Header("Authorization") token: String, @Body novoCliente: CadastroClienteVO): Call<Void>

    companion object {
        var BASE_URL = "https://apinewsmarttools.herokuapp.com/"
        fun criar(): CadastroClienteService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(CadastroClienteService::class.java)
        }

    }
}