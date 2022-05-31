package com.example.projetosmarttools.Login.Service

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginMecanico {

    @POST("autenticacao")
    fun post(@Body loginRequest: LoginMecanicoVO): Call<LogingResponse>

    companion object {

        var BASE_URL = "https://apinewsmarttools.herokuapp.com/"

        fun efetuar(): LoginMecanico {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(LoginMecanico::class.java)
        }

    }
}