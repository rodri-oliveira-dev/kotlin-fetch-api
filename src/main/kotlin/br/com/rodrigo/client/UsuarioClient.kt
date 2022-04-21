package br.com.rodrigo.client

import br.com.rodrigo.configuration.ForumApiConfiguration
import br.com.rodrigo.model.Usuario
import br.com.rodrigo.response.ForumResponseBase
import br.com.rodrigo.response.UsuarioResponse
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Headers
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

@Client(ForumApiConfiguration.API_URL)
@Headers(
    Header(name = HttpHeaders.USER_AGENT, value = ForumApiConfiguration.USER_AGENT),
    Header(name = HttpHeaders.ACCEPT, value = ForumApiConfiguration.ACCEPT)
)
interface UsuarioClient {

    @Post("/usuarios")
    fun cadastrar(
        @Header("Authorization") authorization: String,
        @Body usuario: Usuario
    ): HttpResponse<ForumResponseBase<UsuarioResponse>>
}

