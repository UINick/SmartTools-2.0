package com.example.projetosmarttools.Clientes

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface ClienteService {

    @GET("clientes")
    fun fetchClients(@Header("Authorization") token: String): Call<List<ClienteVO>>

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