package com.example.projetosmarttools.Cadastro.Service.Oficina

data class CadastroOficinaVO(
    var nomeOficina: String,
    var email: String,
    var senha: String,
    val cnpj: String,
    var telefone: String
)