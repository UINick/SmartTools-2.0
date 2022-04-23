package com.example.projetosmarttools.Cadastro.Service.Oficina

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface CadastroOficina {

    @POST("usuarios")
    fun post(@Body novoUsuario: CadastroOficinaVO): Call<Void>

    companion object {
        var BASE_URL = "https://apirest-smarttools.herokuapp.com/swagger-ui/"
        fun criar(): CadastroOficina {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(CadastroOficina::class.java)
        }

    }
}