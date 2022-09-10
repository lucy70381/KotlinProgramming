package com.example.KotlinProgramming

//Frame Extension
fun String.frame(padding: Int, formatChar: String = "*"): String {
    val greeting = "$this!"
    val middle = formatChar.padEnd(padding)
            .plus(greeting)
            .plus(formatChar.padStart(padding))
    val end = (0 until middle.length).joinToString("") { formatChar }
    return "$end\n$middle\n$end"
}

fun main(args: Array<String>) {
    print("Welcome, Madrigal".frame(5))
}