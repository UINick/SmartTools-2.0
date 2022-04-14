package com.example.projetosmarttools.Cadastro.Service.Oficina

data class CadastroOficinaVO(
    var nomeOficina: String,
    var emailOficina: String,
    var senhaOficina: String,
    val cnpjOficina: String,
    var telefoneOficina: String
)