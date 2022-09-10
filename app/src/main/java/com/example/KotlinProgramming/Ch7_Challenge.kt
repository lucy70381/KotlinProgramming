package com.example.KotlinProgramming

fun main(args: Array<String>) {
    val phrase = "DRAGON'S BREATH: IT'S GOT WHAT ADVENTURERS CRAVE!"
    println(toDragonSpeak(phrase))
}

private fun toDragonSpeak(phrase: String) =
        phrase.replace(Regex("[aeiouAEIOU]")) {
            when(it.value.toLowerCase()) {
                "a" -> "4"
                "e" -> "3"
                "i" -> "1"
                "o" -> "0"
                "u" -> "|_|"
                else -> it.value
            }
        }