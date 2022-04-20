package br.com.rodrigo.dto

import br.com.rodrigo.annotation.ForcaSenha
import br.com.rodrigo.model.Usuario
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class UsuarioInput(
    @field:NotBlank val nome: String?,
    @field:NotBlank val email: String?,
    @field:NotBlank @field:ForcaSenha val password: String?
) {

    fun paraUsuario(): Usuario {
        return Usuario(nome!!, email!!, password!!)
    }
}