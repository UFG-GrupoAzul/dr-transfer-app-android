package br.ufg.inf.drtransferapp.patient.commons.utils.extension

import br.ufg.inf.drtransferapp.patient.commons.utils.enum.Genero
import br.ufg.inf.drtransferapp.patient.commons.utils.enum.TipoSanguineo
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.calculateAgeFromISODate(): String {
    return try {
        val formatter = DateTimeFormatter.ISO_INSTANT
        val date = LocalDate.parse(this, formatter)
        val currentYear = LocalDate.now().year
        val birthYear = date.year
        (currentYear - birthYear).toString()
    } catch (e: Exception) {
        "Data inválida"
    }
}
