package com.delete.test.data.mapper

import com.delete.test.domain.model.response.NetError
import com.delete.test.domain.model.response.NetworkError

fun Throwable.toNetworkError(): NetworkError {
    val error =  when(this){
        else -> NetError.UnknownError
    }
    return NetworkError(error, this)
}