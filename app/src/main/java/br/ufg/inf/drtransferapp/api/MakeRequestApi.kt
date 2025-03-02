package br.ufg.inf.drtransferapp.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> makeRequest(requestFunction:() -> T) : Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            Result.success(requestFunction())
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}