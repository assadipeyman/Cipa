package com.cipa.cipamerchant.utils

import java.text.DecimalFormat
import java.text.NumberFormat

object StringUtils {
    fun toCurrencyFormat(currency:Double) : String {
        val formatter: NumberFormat = DecimalFormat("#,###")
        return formatter.format(currency)
    }
}