package com.example.projetosmarttools.Clientes

data class ClienteVO(
    val nome: String,
    val telefone: String,
    val id: Int
)

data class DetalheClienteVO(
    val nome: String,
    val telefone: String,
    val cpf: String,
    val email:String
)

