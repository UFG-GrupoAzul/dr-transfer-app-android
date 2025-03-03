package br.ufg.inf.drtransferapp.patient.commons.utils.extension

fun <LET> LET?.orElse(block: () -> LET) = this ?: block()