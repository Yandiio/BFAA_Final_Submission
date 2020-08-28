package com.dicoding.github.lastsubmission.core.util

import com.bumptech.glide.load.HttpException
import com.dicoding.github.lastsubmission.core.state.ResultState
import java.io.IOException
import java.net.ConnectException

suspend fun <T : Any> safeApiCall(apiCall: suspend () -> ResultState<T>): ResultState<T> {
    return try {
        apiCall.invoke()
    } catch (t: Throwable) {
        when (t) {
            is IOException -> ResultState.NetworkError
            is HttpException -> {
                val code = t.statusCode
                val errorResponse = t.message
                return ResultState.Error(errorResponse, code)
            }
            is ConnectException -> ResultState.NetworkError
            else -> ResultState.Error(null, 500)
        }
    }
}