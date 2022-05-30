package com.example.projetosmarttools.Clientes

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ClienteService {

    @GET("clientes")
    fun fetchClients(@Header("Authorization") token: String): Call<List<ClienteVO>>

    @GET("clientes/{path_variable}")
    fun fetchClientById(@Path("path_variable") number: Int, @Header("Authorization") token: String): Call<DetalheClienteVO>

    companion object {
        var BASE_URL = "https://apinewsmarttools.herokuapp.com/"
        fun getAllClients(): ClienteService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ClienteService::class.java)
        }

    }
}