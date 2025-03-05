package br.ufg.inf.drtransferapp.patient.commons.utils.format

import java.util.regex.Pattern

object FormatCpf: RegexReplaces() {

    private val CPF_FORMATADO: Pattern = Pattern.compile("(\\d{3})[.](\\d{3})[.](\\d{3})-(\\d{2})")
    private val CPF_DESFORMATADO: Pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})")
    private const val CPF_REPLACEMENT = "$1.$2.$3-$4"

    fun formataCpf(cpf: String): String {
        return if (CPF_FORMATADO.isEqual(cpf)) {
            cpf
        } else {
            matchAndReplace(CPF_DESFORMATADO.matcher(cpf), CPF_REPLACEMENT) ?: cpf
        }
    }
}