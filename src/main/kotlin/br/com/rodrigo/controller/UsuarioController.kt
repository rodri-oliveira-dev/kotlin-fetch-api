package br.com.rodrigo.controller

import br.com.rodrigo.client.AutenticacaoClient
import br.com.rodrigo.client.UsuarioClient
import br.com.rodrigo.dto.UsuarioInput
import br.com.rodrigo.model.DadosLogin
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Controller("/usuarios")
@Validated
class UsuarioController(
    private val autenticacaoClient: AutenticacaoClient,
    private val usuarioClient: UsuarioClient
) {

    @Post(produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
    fun criar(@Body @Valid usuario: UsuarioInput): HttpResponse<Any> {
        try {
            val resultCredenciais = autenticacaoClient.recuperarCredenciais(DadosLogin("12345678", "rodrigo@email.com"))
            val credenciais = resultCredenciais.header("Authorization")

            val result = usuarioClient.cadastrar(credenciais, usuario.paraUsuario())

            return if (result.body() == null) {
                HttpResponse.badRequest()
            } else {
                HttpResponse.ok(result.body())
            }
        } catch (ex: Exception) {
            return HttpResponse.badRequest()
        }
    }
}