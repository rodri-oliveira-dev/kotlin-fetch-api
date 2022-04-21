package br.com.rodrigo.configuration

import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.context.annotation.Requires

@ConfigurationProperties(ForumApiConfiguration.PREFIX)
@Requires(property = ForumApiConfiguration.PREFIX)
class ForumApiConfiguration {
    companion object {
        const val PREFIX = "forum-api"
        const val API_URL = "http://localhost:8030"
        const val USER_AGENT = "Micronaut HTTP Client"
        const val ACCEPT = "application/json"
    }
}