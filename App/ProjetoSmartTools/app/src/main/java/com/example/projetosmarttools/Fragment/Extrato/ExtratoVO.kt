package com.example.projetosmarttools.Fragment.Extrato

data class ExtratoVO(
    var dataRegistro:String,
    var valor: Double,
    var tipoLancamento: String
)

data class ExtratoCadastro(
    val categoria: String,
    val descricao: String,
    val valor: Double
)