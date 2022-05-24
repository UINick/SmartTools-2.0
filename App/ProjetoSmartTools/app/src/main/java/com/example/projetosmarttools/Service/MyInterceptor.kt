package com.example.projetosmarttools.Service

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("token", "")
            .addHeader("tipo", "")
            .build()
        return chain.proceed(request)
    }
}