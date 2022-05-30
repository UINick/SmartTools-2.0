package com.example.projetosmarttools.CadastroVeiculo

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface VeiculoService {

    @POST("veiculos")
    fun postNewVeiculo(@Header("Authorization") token: String, @Body novoVeiculo: VeiculoVO): Call<Void>

    companion object {
        var BASE_URL = "https://apinewsmarttools.herokuapp.com/"
        fun veiculo(): VeiculoService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(VeiculoService::class.java)
        }

    }
}