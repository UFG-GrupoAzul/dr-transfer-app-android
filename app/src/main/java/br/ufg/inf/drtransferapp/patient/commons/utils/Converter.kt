package br.ufg.inf.drtransferapp.patient.commons.utils

import br.ufg.inf.drtransferapp.patient.commons.utils.enum.Genero
import br.ufg.inf.drtransferapp.patient.commons.utils.enum.TipoSanguineo

fun convertGenderType(gender: String): String {
    return when(gender) {
        Genero.MALE.toString() -> "Masculino"
        Genero.FEMALE.toString() -> "Feminino"
        else -> "Não informado"
    }
}

fun convertBloodtype(bloodtype: String): String {
    return when (bloodtype) {
        TipoSanguineo.A_POSITIVE.toString() -> "A+"
        TipoSanguineo.A_NEGATIVE.toString() -> "A-"
        TipoSanguineo.B_POSITIVE.toString() -> "B+"
        TipoSanguineo.B_NEGATIVE.toString() -> "B-"
        TipoSanguineo.AB_POSITIVE.toString() -> "AB+"
        TipoSanguineo.AB_NEGATIVE.toString() -> "AB-"
        TipoSanguineo.O_POSITIVE.toString() -> "O+"
        TipoSanguineo.O_NEGATIVE.toString() -> "O-"
        else -> "Não informado"
    }
}

fun convertBloodtypeReverse(bloodtype: String): String {
    return when (bloodtype) {
        "A+" -> TipoSanguineo.A_POSITIVE.toString()
        "A-" -> TipoSanguineo.A_NEGATIVE.toString()
        "B+" -> TipoSanguineo.B_POSITIVE.toString()
        "B-" -> TipoSanguineo.B_NEGATIVE.toString()
        "AB+" -> TipoSanguineo.AB_POSITIVE.toString()
        "AB-" -> TipoSanguineo.AB_NEGATIVE.toString()
        "O+" -> TipoSanguineo.O_POSITIVE.toString()
        "O-" -> TipoSanguineo.O_NEGATIVE.toString()
        else -> "Não informado"
    }
}