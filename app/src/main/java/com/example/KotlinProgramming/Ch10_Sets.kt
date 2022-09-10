package com.example.KotlinProgramming

import java.io.File

fun main(args: Array<String>) {
    //Trying to create a set with a duplicate
    val planets = setOf("Mercury", "Venus", "Earth", "Earth")

    println(planets)

    //Checking planets
    println(planets.contains("Earth"))

    //Finding the third planet
    println(planets.elementAt(2))

    //Adding elements to a set
    val menuList = File(System.getProperty("user.dir") + "/data/file.txt")
            .readText()
            .split("\n")

    val patronList = listOf("Eli", "Mordoc", "Sophie")
    val uniquePatrons = mutableSetOf<String>()
    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = menuList.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }

    //while Loops
    var orderCount = 0
    while (orderCount < uniquePatrons.size) {
        println(uniquePatrons.elementAt(orderCount))
        orderCount++
    }
}