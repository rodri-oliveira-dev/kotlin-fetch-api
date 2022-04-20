package br.com.rodrigo.client

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

@Client(value = "http://localhost:8030/usuarios")
@Headers(
    Header(name = HttpHeaders.USER_AGENT, value = "Micronaut HTTP Client"),
    Header(name = HttpHeaders.ACCEPT, value = "application/json")
)
interface UsuarioClient {

    @Post
    fun cadastrar(
        @Header("Authorization") authorization: String,
        @Body usuario: Usuario
    ): HttpResponse<ForumResponseBase<UsuarioResponse>>
}

