package com.example.projetosmarttools.Service

import com.example.projetosmarttools.Login.Service.LoginMecanicoVO
import com.example.projetosmarttools.Login.Service.LogingResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

     @POST(Constants.LOGIN_URL)
     @FormUrlEncoded
     fun login(@Body request: LoginMecanicoVO): Call<LogingResponse>

     @POST(Constants.CADASTRO_URL)
     @FormUrlEncoded
     fun cadastro(@Body request: CadastroRequest): Call<CadastroResponse>



}