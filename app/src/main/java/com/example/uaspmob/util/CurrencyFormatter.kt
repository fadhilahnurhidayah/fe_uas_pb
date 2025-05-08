package com.example.uaspmob.util

import java.text.NumberFormat
import java.util.*

object CurrencyFormatter {
    private val rupiahFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale("in", "ID")).apply {
        maximumFractionDigits = 0
        currency = Currency.getInstance("IDR")
    }

    /**
     * Formats amount to Indonesian Rupiah currency format
     * Example: 500000 → "Rp 500.000"
     */
    fun format(amount: Int): String {
        return rupiahFormat.format(amount.toLong())
    }

    /**
     * Formats amount without currency symbol
     * Example: 500000 → "500.000"
     */
    fun formatWithoutSymbol(amount: Int): String {
        return rupiahFormat.format(amount.toLong()).replace("Rp", "").trim()
    }

    /**
     * Formats amount with custom prefix/suffix
     * Example: formatWithCustom(500000, "IDR ", "") → "IDR 500.000"
     */
    fun formatWithCustom(amount: Int, prefix: String = "", suffix: String = ""): String {
        return "$prefix${formatWithoutSymbol(amount)}$suffix"
    }

    /**
     * Parses formatted Rupiah string back to numeric value
     * Example: "Rp 500.000" → 500000
     * @throws NumberFormatException if the string cannot be parsed
     */
    fun parse(formattedString: String): Int {
        val cleanString = formattedString
            .replace("Rp", "")
            .replace(".", "")
            .trim()
        return cleanString.toInt()
    }

    /**
     * Formats amount to compact/shortened form
     * Example: 1500000 → "Rp 1,5 jt"
     */
    fun formatCompact(amount: Int): String {
        return when {
            amount >= 1_000_000 -> "Rp ${amount / 1_000_000},${(amount % 1_000_000) / 100_000} jt"
            amount >= 1_000 -> "Rp ${amount / 1_000} rb"
            else -> format(amount)
        }
    }
}