package ru.netology

fun main() {
    val amount = 5700.0
    val commission: Double = if (0.0075 * amount > 35.0) 0.0075 * amount else 35.0

    println(commission)
}