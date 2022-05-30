package com.example.projetosmarttools.Fragment.Extrato

data class ExtratoVO(
    val id: Int,
    var dataRegistro:String,
    var valor: Double,
    var tipoLancamento: String,
    val categoria: CategoriaExtratoVO,
    val usuario: UsuarioVO
)

data class ExtratoDetalheVO(
    val id: Int,
    var dataRegistro:String,
    var valor: Double,
    val categoria: CategoriaExtratoVO,
    val descricao: String,
)

data class CategoriaExtratoVO(
    val id: Int,
    val nome: String
)

data class ExtratoCadastro(
    val categoria: String,
    val descricao: String,
    val valor: Double
)

data class ResumoLancamentoVO(
    val valorTotalReceitas: Double,
    val valorTotalDespesas: Double
)

data class UsuarioVO(
    val id: Int,
    val nomeOficina: String
)