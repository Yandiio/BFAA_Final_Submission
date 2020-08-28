package com.dicoding.github.lastsubmission.core.state

sealed class ResultState<out T : Any> {
    data class success<out T: Any>(val data: T) : ResultState<T>()
    data class Error(val error: String?, val statusCode: Int): ResultState<Nothing>()
    object NetworkError: ResultState<Nothing>()
}