package br.com.rodrigo.model


data class UsuarioAtualizacao(
    val id: Long,
    val nome: String,
    val email: String,
    val password: String = "Teste@3456"
)