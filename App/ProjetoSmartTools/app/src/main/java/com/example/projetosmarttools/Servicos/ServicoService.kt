package com.example.projetosmarttools.Servicos

import com.example.projetosmarttools.CadastroVeiculo.VeiculoVO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ServicoService {

    @POST("veiculos")
    fun postNewVeiculo(@Header("Authorization") token: String, @Body novoVeiculo: ServicoVO): Call<Void>

    @GET("veiculos/{path_variable}")
    fun fetchVeiculoById(@Header("Authorization") token: String, @Path("path_variable") id: Int ): Call<VeiculoVO>

    companion object {
        var BASE_URL = "https://apinewsmarttools.herokuapp.com/"
        fun servico(): ServicoService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ServicoService::class.java)
        }

    }
}