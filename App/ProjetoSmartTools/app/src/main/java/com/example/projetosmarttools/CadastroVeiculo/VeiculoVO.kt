package com.example.projetosmarttools.CadastroVeiculo

data class VeiculoVO(
    val idVeiculo: Int? = null,
    val cpfCliente: String,
    val marcaVeiculo : String,
    val modeloVeiculo: String,
    val nomeCliente: String,
    val placaVeiculo: String,
    val tipoVeiculo: String
)
