package com.example.projetosmarttools

import com.example.projetosmarttools.Cadastro.Service.Oficina.CadastroOficinaVO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface InfoService {

    @GET("usuarios")
    fun buscarInfo(@Header("Authorization") token: String): Call<CadastroOficinaVO>

    companion object {
        var BASE_URL = "https://apinewsmarttools.herokuapp.com/"
        fun info(): InfoService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(InfoService::class.java)
        }

    }

}