package br.ufg.inf.drtransferapp.api

interface ApiListener<T> {
    fun onSuccess(data: T)
    fun onError(error: Throwable)
}