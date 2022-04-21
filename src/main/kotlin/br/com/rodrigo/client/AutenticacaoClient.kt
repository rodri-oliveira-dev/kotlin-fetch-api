package br.com.rodrigo.client

import br.com.rodrigo.configuration.ForumApiConfiguration
import br.com.rodrigo.model.DadosLogin
import io.micronaut.http.HttpHeaders.ACCEPT
import io.micronaut.http.HttpHeaders.USER_AGENT
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Headers
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

@Client(ForumApiConfiguration.API_URL)
@Headers(
    Header(name = USER_AGENT, value = ForumApiConfiguration.USER_AGENT),
    Header(name = ACCEPT, value = ForumApiConfiguration.ACCEPT)
)
interface AutenticacaoClient {

    @Post("/login")
    fun recuperarCredenciais(@Body usuario: DadosLogin): HttpResponse<Any>
}