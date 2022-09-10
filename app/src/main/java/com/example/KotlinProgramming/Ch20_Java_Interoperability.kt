// 20.20 Specifying compiled class name using JvmName
@file:JvmName("Hero")
package com.example.KotlinProgramming

import java.io.IOException
import java.lang.Exception

//20.2 Declaring a main function and Jhava adversary in Kotlin
fun main(args: Array<String>) {
    val adversary = Ch20_Java_Interoperability()

    // 20.3 Invoking a Java method in Kotlin
    println(adversary.utterGreeting())

    // 20.5 Printing the friendship level
    val friendshipLevel = adversary.determineFriendshipLevel()
    // 20.7 Handling nullability with the safe call operator
    // 20.8 Providing a default value with the Elvis operator
    println(friendshipLevel?.toLowerCase() ?: "It's complicated.")

    // 20.11 Referencing a Java field from Kotlin
    val adversaryHitPoints = adversary.hitPoints
    // 20.12 Referencing a Java field from Kotlin
    println(adversaryHitPoints.dec())
    // 20.13 Java backing class name
    println(adversaryHitPoints.javaClass)

    // 20.16 Setting a Java field from Kotlin
    adversary.greeting = "Hello, Hero"
    println(adversary.utterGreeting())

    // 20.25 Testing out offerFood
    adversary.offerFood()

    // 20.36 Handling exceptions using try/catch
    try {
        // 20.35 Invoking a method that throws an exception
        adversary.extendHandInFriendship()
    } catch (e: Exception) {
        println("Begone, foul beast!")
    }
}

// 20.17 Declaring a top-level function in Kotlin
fun makeProclamation() = "Greetings, beast!"

// 20.22 Adding a function with default parameters
// 20.24 Adding @JvmOverloads (Bytecode) -> 多載
@JvmOverloads
fun handOverFood(leftHand: String = "berries", rightHand: String = "beef") {
    println("Hmmm... you hand over some delicious $leftHand and $rightHand")
}

// 20.26 Declaring the Spellbook class
class Spellbook {
    // 20.27 Applying the @JvmField annotation -> Java可直接存取該變數
    @JvmField
    val spells = listOf("Magic Ms. L", "Lay on Hans")

    // 20.29 Adding a companion object to Spellbook
    companion object {
        // 20.31 Adding the @JvmField annotation to the member of a companion object
        @JvmField
        val MAX_SPELL_COUNT = 10

        // 20.32 Using @JvmStatic on a function
        @JvmStatic
        fun getSpellbookGreeting() = println("I am the Great Grimoire!")
    }
}

// 20.37 Throwing an unchecked exception
//20.39 Using the @Throws annotation
@Throws(IOException::class)
fun acceptApology() {
    throw IOException()
}

// 20.40 Defining the translator function type
val translator = { utterance: String ->
    println(utterance.toLowerCase().capitalize())
}