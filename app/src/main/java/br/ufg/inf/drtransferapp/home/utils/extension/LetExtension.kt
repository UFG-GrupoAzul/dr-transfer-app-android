package br.ufg.inf.drtransferapp.home.utils.extension

fun <LET> LET?.orElse(block: () -> LET) = this ?: block()