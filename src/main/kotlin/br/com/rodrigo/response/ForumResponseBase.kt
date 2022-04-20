package br.com.rodrigo.response

data class ForumResponseBase<T>(
    val data: T,
    val errors: List<Any>,
    val success: Boolean
)