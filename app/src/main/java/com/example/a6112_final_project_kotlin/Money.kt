package com.example.a6112_final_project_kotlin


fun centsToDollars(cents: Int): String {
    val dollars = cents / 100
    val remainingCents = cents % 100
    return String.format("$%.2f", dollars + (remainingCents / 100.0))
}

fun dollarsToCents(dollarString: String): Int {
    try {
        // Remove '$', ',', and whitespace
        val cleanedString = dollarString.replace("[$,\\s]".toRegex(), "")

        // Convert to a double and handle potential errors
        val dollarAmount = cleanedString.toDouble()
        if (dollarAmount < 0) {
            throw IllegalArgumentException("Dollar amount cannot be negative.")
        }

        // Convert dollars to cents and round to the nearest integer
        return (dollarAmount * 100).toInt()
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("Invalid dollar string format.")
    }
}
