package br.ufg.inf.drtransferapp.patient.commons.utils.format

import java.util.regex.Matcher
import java.util.regex.Pattern

open class RegexReplaces {

    fun matchAndReplace(matcher: Matcher, replacement: String): String? {
        return if (matcher.matches()) {
            matcher.replaceAll(replacement)
        } else {
            null
        }
    }

    fun Pattern.isEqual(compareTo: String): Boolean = this.matcher(compareTo).matches()
}