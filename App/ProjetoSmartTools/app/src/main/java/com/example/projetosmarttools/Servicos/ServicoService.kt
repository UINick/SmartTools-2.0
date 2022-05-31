package com.example.projetosmarttools.Servicos

import com.example.projetosmarttools.Cadastro.CadastroVeiculo.VeiculoVO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ServicoService {

    @PUT("servicos/{path_variable}")
    fun alterarStatus(@Header("Authorization") token: String, @Path("path_variable") id: Int, @Body status: StatusServicoVO): Call<Void>

    @POST("servicos")
    fun postNewServico(@Header("Authorization") token: String, @Body novoServico: DetalheServicoVO): Call<Void>

    @GET("servicos")
    fun fetchAllServices(@Header("Authorization") token: String): Call<List<ServicoDetailsVO>>

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