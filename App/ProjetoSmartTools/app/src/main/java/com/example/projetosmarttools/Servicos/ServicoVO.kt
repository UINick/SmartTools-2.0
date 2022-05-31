package com.example.projetosmarttools.Servicos

data class ServicoVO(
    val ordemServico: Int,
    val valorServico: Double,
    val descricao: String,
    val dataServico:String
)

data class DetalheServicoVO(
    val categoria: String,
    val descricao: String,
    val placa: String,
    val valorServico: Double
)

data class ServicoDetailsVO(
    val id: Int,
    val ordemServico: Int,
    val valorServico: Double,
    val descricao: String,
    val dataServico:String,
    val statusServico: String
)

data class StatusServicoVO(
    val statusServico: String
)