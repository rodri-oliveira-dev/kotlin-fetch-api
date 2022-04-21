package br.com.rodrigo.controller

import br.com.rodrigo.client.AutenticacaoClient
import br.com.rodrigo.client.UsuarioClient
import br.com.rodrigo.dto.UsuarioInput
import br.com.rodrigo.model.DadosLogin
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Controller("/usuarios")
@Validated
class UsuarioController(
    private val autenticacaoClient: AutenticacaoClient,
    private val usuarioClient: UsuarioClient
) {
    @Get("{id}", produces = [MediaType.APPLICATION_JSON])
    fun recuperaPorId(@PathVariable id: Long): HttpResponse<Any> {
        val credenciais = recuperaToken()
        val result = usuarioClient.recuperaPorId(credenciais!!, id)

        return if (result.body() == null) {
            HttpResponse.badRequest()
        } else {
            val usuario = result.body()
            HttpResponse.ok(usuario)
        }
    }

    @Get(produces = [MediaType.APPLICATION_JSON])
    fun recuperaTodos(): HttpResponse<Any> {
        val credenciais = recuperaToken()
        val result = usuarioClient.recuperaTodos(credenciais!!)

        return if (result.body() == null) {
            HttpResponse.badRequest()
        } else {
            val usuarios = result.body()
            HttpResponse.ok(usuarios.data)
        }
    }

    @Post(produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
    fun criar(@Body @Valid usuario: UsuarioInput): HttpResponse<Any> {
        val credenciais = recuperaToken()
        val result = usuarioClient.cadastrar(credenciais!!, usuario.paraUsuario())

        return if (result.body() == null) {
            HttpResponse.badRequest()
        } else {
            val usuario = result.body()
            val uri = UriBuilder.of("/usuarios/{id}").expand(mutableMapOf(Pair("id", usuario.data.id)))
            HttpResponse.created(usuario, uri)
        }
    }

    private fun recuperaToken(): String? {
        val resultCredenciais = autenticacaoClient.recuperarCredenciais(DadosLogin("12345678", "rodrigo@email.com"))
        return resultCredenciais.header("Authorization")
    }
}