package br.com.rodrigo.client

import br.com.rodrigo.model.DadosLogin
import io.micronaut.http.HttpHeaders.ACCEPT
import io.micronaut.http.HttpHeaders.USER_AGENT
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Headers
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

@Client(value = "http://localhost:8030/login")
@Headers(
    Header(name = USER_AGENT, value = "Micronaut HTTP Client"),
    Header(name = ACCEPT, value = "application/json")
)
interface AutenticacaoClient {

    @Post
    fun recuperarCredenciais(@Body usuario: DadosLogin): HttpResponse<Any>
}