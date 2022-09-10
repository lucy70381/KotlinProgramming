package com.example.KotlinProgramming

import java.lang.Exception
import java.lang.IllegalStateException

fun main(args: Array<String>) {

    //safeCallOperator()

    //safeCallWithLet()

    //doubleBangOperator()

    //nullChecking()

    //nullCoalescingOperator()

    throwException()
}

//Using the safe call operator
private fun safeCallOperator() {
    var beverage = readLine()?.capitalize()
    println(beverage)
}

//Using safe calls with let     ?.let {}
private fun safeCallWithLet() {
    var beverage = readLine()?.let {
        if (it.isNotBlank()) {
            it.capitalize()
        } else {
            "Buttered Ale"
        }
    }
    println(beverage)
}

//Using the double-bang operator    !!.
private fun doubleBangOperator() {
    var beverage: String? = null
    println(beverage!!.capitalize())
}

//Using != null for null checking
private fun nullChecking() {
    var beverage: String? = null

    if (beverage != null) {
        beverage = beverage.capitalize()
    } else {
        println("I can't do that without crashing - beverage was null!")
    }
    println(beverage)
}

//Using the null coalescing operator (Elvis operator)   ?:
private fun nullCoalescingOperator() {
    var beverage: String? = null

    beverage?.let {
        beverage = it.capitalize()
    } ?: println("I can't do that without crashing - beverage was null!")

    val beverageServed: String = beverage ?: "Buttered Ale"
    println(beverageServed)
}

//Adding a try/catch statement
private fun throwException() {
    var swordsJuggling: Int? = null

    try {
        proficiencyCheck(swordsJuggling)
        swordsJuggling = swordsJuggling!!.plus(1)
    } catch (e: Exception) {
        println(e)
    }

    try {
        juggleSwords(2)
    } catch (e: Exception) {
        println(e)
    }
}

fun proficiencyCheck(swordsJuggling: Int?) {
    //swordsJuggling ?: throw UnskilledSwordJugglerException()

    //Preconditions: checkNotNull -> Throws an IllegalStateException if argument is null.
    checkNotNull(swordsJuggling, { "Player cannot juggle swords" })
}

//Preconditions: require -> Throws an IllegalArgumentException if argument is false.
fun juggleSwords(swordsJuggling: Int) {
    require(swordsJuggling >= 3, { "Juggle at least 3 swords to be exciting." })
}

//custom exception
class UnskilledSwordJugglerException():
        IllegalStateException("Player caanot juggle swords")

