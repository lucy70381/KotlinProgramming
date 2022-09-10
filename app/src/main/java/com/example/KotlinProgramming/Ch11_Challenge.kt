package com.example.KotlinProgramming

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.File

private val patronGold = mutableMapOf<String, Double>()
private val uniquePatrons = mutableSetOf<String>()

fun main(args: Array<String>) {
    val patronList = listOf("Eli", "Mordoc", "Sophie")
    val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
    val menuList = File(System.getProperty("user.dir") + "/data/file.txt")
        .readText()
        .split("\n")

    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }

    //Adding Entries to a Map
    uniquePatrons.forEach{
        patronGold[it] = 6.0
    }
    println(uniquePatrons)
    println(patronGold)

    var orderCount = 0
    while (orderCount <= 9) {
        placeOrder(uniquePatrons.shuffled().first(),
            menuList.shuffled().first())
        orderCount++
    }

    displayPatronBalances()
}

private fun displayPatronBalances() {
    patronGold.forEach { (patron, balance) ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}

private fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    if (totalPurse >= price) {
        patronGold[patronName] = totalPurse - price
    } else {
        //If a patron lacks sufficient funds,
        //boot them out onto the mean streets of NyetHack by removing them from uniquePatrons and the patronGold map.
        patronGold.remove(patronName)
        uniquePatrons.remove(patronName)
    }
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
    performPurchase(price.toDouble(), patronName)
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