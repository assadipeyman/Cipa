package com.cipa.cipamerchant.utils

import java.text.DecimalFormat
import java.text.NumberFormat

object StringUtils {

    val Number.withPersianDigits: String
        get() = "$this".withPersianDigits

    val String.withPersianDigits: String
        get() = StringBuilder().also { builder ->
            toCharArray().forEach {
                builder.append(
                    when {
                        Character.isDigit(it) -> PERSIAN_DIGITS["$it".toInt()]
                        it == '.' -> "/"
                        else -> it
                    }
                )
            }
        }.toString().removeDecimalIfZero

    private val PERSIAN_DIGITS = charArrayOf(
        '0' + 1728,
        '1' + 1728,
        '2' + 1728,
        '3' + 1728,
        '4' + 1728,
        '5' + 1728,
        '6' + 1728,
        '7' + 1728,
        '8' + 1728,
        '9' + 1728
    )

    val Number.withCurrencyFormat: String
        get() =   DecimalFormat("#,###").format(this).withPersianDigits

    val String.removeDecimalIfZero :String
        get() {
            if(this.endsWith(".0") || this.endsWith("/" + ('0' + 1728).toString()))
                return this.substring(0 , this.length-2)
            return this
        }
}