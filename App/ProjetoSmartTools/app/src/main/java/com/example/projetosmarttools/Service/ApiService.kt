package com.example.projetosmarttools.Service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

     @POST(Constants.LOGIN_URL)
     @FormUrlEncoded
     fun login(@Body request: LoginRequest): Call<LoginResponse>

     @POST(Constants.CADASTRO_URL)
     @FormUrlEncoded
     fun cadastro(@Body request: CadastroRequest): Call<CadastroResponse>



}