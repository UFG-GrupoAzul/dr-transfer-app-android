package br.ufg.inf.drtransferapp.utils.extension

fun <LET> LET?.orElse(block: () -> LET) = this ?: block()