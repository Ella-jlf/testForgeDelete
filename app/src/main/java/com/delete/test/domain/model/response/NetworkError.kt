package com.delete.test.domain.model.response

data class NetworkError(
    val error: NetError,
    val t: Throwable? = null,
)

enum class NetError(val message: String){
    UnknownError("UnknownError")
}
