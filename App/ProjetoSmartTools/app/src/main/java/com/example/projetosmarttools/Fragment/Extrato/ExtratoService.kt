package com.example.projetosmarttools.Fragment.Extrato

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface ExtratoService {

    @GET("lancamentos")
    fun fetchLancamentos(@Header("Authorization") token: String): Call<List<ExtratoVO>>

    companion object {
        var BASE_URL = "https://apinewsmarttools.herokuapp.com/"
        fun getLancamentos(): ExtratoService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ExtratoService::class.java)
        }

    }
}