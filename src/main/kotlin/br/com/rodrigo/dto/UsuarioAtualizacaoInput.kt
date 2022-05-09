package br.com.rodrigo.dto

import br.com.rodrigo.model.UsuarioAtualizacao
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@Introspected
data class UsuarioAtualizacaoInput(
    @field:Min(1) val id: Long,
    @field:NotBlank val nome: String?,
    @field:NotBlank val email: String?
) {

    fun paraUsuarioAtualizacao(): UsuarioAtualizacao {
        return UsuarioAtualizacao(id, nome!!, email!!)
    }
}