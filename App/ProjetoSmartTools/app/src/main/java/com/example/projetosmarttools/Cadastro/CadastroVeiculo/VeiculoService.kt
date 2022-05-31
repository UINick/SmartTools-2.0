package com.example.projetosmarttools.Cadastro.CadastroVeiculo

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface VeiculoService {

    @POST("veiculos")
    fun postNewVeiculo(@Header("Authorization") token: String, @Body novoVeiculo: VeiculoVO): Call<Void>

    @GET("veiculos")
    fun fetchVeiculoByPlaca(@Header("Authorization") token: String, @Query("placa") placa: String): Call<VeiculoVO>

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