package com.example.KotlinProgramming

import java.io.File

//const val TAVERN_NAME = "Taernyl's Folly"


fun main(args: Array<String>) {

    val patronList = listOf("Eli", "Mordoc", "Sophie")  //read-only
    println(patronList.first())
    println(patronList.last())
    println()

    //Testing getOrElse
    println(patronList.getOrElse(4) {"Unknown Patron"})    //patronList.getOrNull(4) ?: "Unknown Patron"
    println()

    //Checking the contents of a list
    if (patronList.contains("Eli")) {
        println("The tavern master says: Eli's in the back playing cards.")
    } else {
        println("The tavern master says: Eli isn't here.")
    }
    println()

    //Checking the contents of a list
    if (patronList.containsAll(listOf("Sophie", "Mordoc"))) {
        println("The tavern master says: Yea, they're seated by the stew kettle.")
    } else {
        println("The tavern master says: Nay, they departed hours ago.")
    }
    println()

    //Changing a list’s contents
    val patronList2 = mutableListOf("Eli", "Mordoc", "Sophie")
    println(patronList2)
    patronList2.remove("Eli")
    patronList2.add("Alex")
    patronList2.add(0, "Alex")
    patronList2[0] = "Alexis"
    println(patronList2)
    println()

    //Iteration
    for (patron in patronList) {
        println("Good evening, $patron")
    }
    println()

    //Iterating over the patronList with forEach
    patronList.forEach { patron ->
        println("Good evening, $patron")
    }
    println()

    //Reading menu data from a file
    val menuList = File(System.getProperty("user.dir") + "/data/file.txt")
            .readText()
            .split("\n")

    //Printing the diversified menu
    menuList.forEachIndexed { index, data ->
        println("$index : $data")
    }
    println()

    //Iterating over the patronList with forEach (含index)
    patronList.forEachIndexed { index, patron ->
        println("Good evening, $patron - you're #${index + 1} in line.")
        placeOrder(patron, menuList.shuffled().first())
    }
    println()

    //_可跳過不想要的元素
    val (goldMedal, _, bronzeMedal) = patronList
    println("$goldMedal $bronzeMedal")

    //Array Types
    val playerAges: IntArray = intArrayOf(34, 27, 14, 52, 101)
    val playerAges2: List<Int> = listOf(34, 27, 14, 52, 101)
    playerAges2.toIntArray()
}

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")

    //Destructuring assignment - 解構賦值
    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price."
    println(message)

    // replace 替換字元
    val phrase = if (name == "Dragon's Breath") {
        "$patronName exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
    } else {
        "$patronName says: Thanks for the $name."
    }
    println(phrase)
}

private fun toDragonSpeak(phrase: String) =
        phrase.replace(Regex("[aeiou]")) {
            when(it.value) {
                "a" -> "4"
                "e" -> "3"
                "i" -> "1"
                "o" -> "0"
                "u" -> "|_|"
                else -> it.value
            }
        }