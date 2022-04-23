package com.example.projetosmarttools.Service

import com.google.gson.annotations.SerializedName

data class LoginRequest (
        @SerializedName("email")
        var email: String,

        @SerializedName("senha")
        var senha: String
    )
