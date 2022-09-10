package com.example.KotlinProgramming

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.File

private val patronGold4 = mutableMapOf<String, Double>()

fun main(args: Array<String>) {
    val patronList = listOf("Eli", "Mordoc", "Sophie")
    val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
    val menuList = File(System.getProperty("user.dir") + "/data/file.txt")
        .readText()
        .split("\n")
    val uniquePatrons = mutableSetOf<String>()
    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }

    //Creating a read-only map
    val patronGold = mapOf("Eli" to 10.5, "Mordoc" to 8.0, "Sophie" to 5.5)
    println(patronGold)

    //Defining a map using the Pair type
    val patronGold2 = mapOf(
        Pair("Eli", 10.5),
        Pair("Mordoc", 8.0),
        Pair("Sophie",5.5)
    )

    //Adding a duplicate key 已存在的key value會被替換
    val patronGold3 = mutableMapOf("Eli" to 5.0, "Sophie" to 1.0)
    patronGold3 += "Sophie" to 6.0
    patronGold3.put("Eli", 3.0)
    patronGold3.putAll(listOf("Jebediah" to 5.0, "Sahara" to 6.0))
    println(patronGold3)

    //Adds an entry for the key if it does not exist already and returns the result; otherwise returns the existing entry.
    patronGold3.getOrPut("123", { 5.0 })
    println(patronGold3)

    //Removes an entry from the map and returns the value.
    patronGold3.remove("123")
    println(patronGold3)

    //Accessing Map Values
    println(patronGold3["Eli"])
    println(patronGold3.getValue("Eli"))    //無對應的key 會報錯
    println(patronGold3.getOrElse("Eli1", {"Unknown"}))

    //Removes all entries from the map.
    patronGold3.clear()
    println(patronGold3)

    //Adding Entries to a Map
    uniquePatrons.forEach{
        patronGold4[it] = 6.0
    }
    println(uniquePatrons)
    println(patronGold4)

    var orderCount = 0
    while (orderCount <= 9) {
        placeOrder(uniquePatrons.shuffled().first(),
            menuList.shuffled().first())
        orderCount++
    }

    displayPatronBalances()
}

private fun displayPatronBalances() {
    patronGold4.forEach { (patron, balance) ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}

private fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold4.getValue(patronName)
    patronGold4[patronName] = totalPurse - price
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