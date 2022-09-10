package com.example.KotlinProgramming

import java.io.File

fun main(args: Array<String>) {

    performApply()
    println(performLet("123"))
    performRun()
    performWith()
    performAlso()
    performTakeIf()
    performTakeUnless()
}

//apply
fun performApply() {
    var menuFile = File("menu-file.txt").apply {
        setReadable(true)       //menuFile.setReadable(true)
        setWritable(true)       //menuFile.setWritable(true)
        setExecutable(false)    //menuFile.setExecutable(false)
    }
}

//let
fun performLet(vipGuest: String?): String {

    //val firstElement = listOf(1,2,3).first()
    //val firstItemSquared = firstElement * firstElement
    val firstItemSquared = listOf(1,2,3).first().let {
        it * it
    }
    println(firstItemSquared)

    //return if (vipGuest != null) {
    //    "Welcome, $vipGuest. Please, go straight back - your table is ready."
    //} else {
    //    "Welcome to the tavern. You'll be seated shortly."
    //}

    return vipGuest?.let {
        "Welcome, $it. Please, go straight back - your table is ready."
    } ?: "Welcome to the tavern. You'll be seated soon."
}

//run
fun performRun() {
    "Polarcubis, Supreme Master of NyetHack"
        .run(::nameIsLong)
        .run(::playerCreateMessage)
        .run(::println)

    val healthPoints = 11
    val status = run {
        if (healthPoints == 100) "perfect health" else "has injuries"
    }
    println(status)
}

fun nameIsLong(name: String) = name.length >= 20
fun playerCreateMessage(nameTooLong: Boolean): String {
    return if (nameTooLong) {
        "Name is too long. Please choose another name."
    } else {
        "Welcome, adventurer"
    }
}

//with
fun performWith() {
    val nameTooLong = with("Name is too long. Please choose another name.") {
        val nameIsLong = length >= 20
        println("$this nameIsLong: $nameIsLong")
    }
}

//also
fun performAlso() {
    var fileContents: List<String>
    File("/Users/lucy70381/Kotlin/KotlinProgramming/app/src/main/java/com/example/KotlinProgramming/file.txt")
            .also {
                println(it.name)
            }.also {
                fileContents = it.readLines()
            }
    println(fileContents)
}

//takeIf
fun performTakeIf() {

    //val file = File("myfile.txt")
    //val fileContents = if (file.canRead() && file.canWrite()) {
    //    file.readText()
    //} else {
    //    null
    //}

    val fileContents = File("/Users/lucy70381/Kotlin/KotlinProgramming/app/src/main/java/com/example/KotlinProgramming/file.txt")
            .takeIf { it.canRead() && it.canWrite() }
            ?.readText()
    println(fileContents)
}

//takeUnless
fun performTakeUnless() {

    val fileContents = File("/Users/lucy70381/Kotlin/KotlinProgramming/app/src/main/java/com/example/KotlinProgramming/file.txt")
            .takeUnless { it.isHidden }
            ?.readText()
    println(fileContents)
}