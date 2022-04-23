package com.example.projetosmarttools.Service

import com.google.gson.annotations.SerializedName

data class CadastroRequest(
    @SerializedName("nomeOficina")
    var nomeOficina: String,

    @SerializedName("cnpj")
    var cnpj: String,

    @SerializedName("telefone")
    var telefone: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("senha")
    var senha: String
)