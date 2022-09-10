package com.example.kotlinProgramming

fun main(args: Array<String>) {
    val name = "Madrigal"
    var healthPoints = 89
    val isBlessed = true
    val isImmortal = false

    // Karma
    val karma = (Math.pow(Math.random(), (110 - healthPoints)/100.0 * 20)).toInt()

    //Aura
    val auraVisible = isBlessed && healthPoints > 50 || isImmortal

    val auraColor = when (karma) {
        in 0..5 -> "red"
        in 6..10 -> "orange"
        in 11..15 -> "purple"
        in 16..20 -> "green"
        else -> "player's health entered incorrectly"
    }

    val healthStatus = when (healthPoints) {
        100 -> "is in excellent condition!"
        in 90..99 -> "has a few scratches." // >=90
        in 75..89 -> if (isBlessed) {   // >=75
            "has some minor wounds but is healing quite quickly!"
        } else {
            "has some minor wounds."
        }
        in 15..74 -> "looks pretty hurt."
        else -> "is in awful condition!"
    }

    // Player status
    val statusFormatString = "(HP: $healthPoints) (Blessed: ${if (isBlessed) "YES" else "NO"}) " +
            "(Arua: $auraColor) (Visible: $auraVisible) -> $name $healthStatus"
    println(statusFormatString)
}