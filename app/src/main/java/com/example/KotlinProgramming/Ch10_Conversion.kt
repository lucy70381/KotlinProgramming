package com.example.KotlinProgramming

fun main(args: Array<String>) {
    //Converting a list to a set
    listOf("Eli Baggins", "Eli Baggins", "Eli Ironfoot").toSet().run(::println)

    //Converting a set back to a list
    listOf("Eli Baggins", "Eli Baggins", "Eli Ironfoot").toSet().toList().run(::println)

    //Calling distinct
    listOf("Eli Baggins", "Eli Baggins", "Eli Ironfoot").distinct().run(::println)


}