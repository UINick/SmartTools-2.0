package com.example.projetosmarttools.Service

import com.google.gson.annotations.SerializedName

data class UserOficina (
        @SerializedName("id")
        var id: String,

        @SerializedName("first_name")
        var firstName: String,

        @SerializedName("last_name")
        var lastName: String,

        @SerializedName("email")
        var email: String
    )
