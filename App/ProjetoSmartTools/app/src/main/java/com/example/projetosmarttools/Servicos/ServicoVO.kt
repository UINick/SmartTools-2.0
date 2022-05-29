package com.example.projetosmarttools.Servicos

data class ServicoVO(
    val ordemServico: Int,
    val valorServico: Double,
    val descricao: String
)

data class DetalheServicoVO(
    val categoria: String,
    val descricao: String,
    val placa: String,
    val valorServico: Double
)