package com.example.projetosmarttools.Service

import com.google.gson.annotations.SerializedName

data class CadastroResponse(
    @SerializedName("status_code")
    var statusCode: Int,
)
