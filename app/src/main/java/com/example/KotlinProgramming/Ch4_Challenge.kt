package com.example.kotlinProgramming

fun main(args: Array<String>) {
    val name = "Madrigal"
    var healthPoints = 89
    val isBlessed = true
    val isImmortal = false

    // Aura
    val auraColor = auraColor(isBlessed, healthPoints, isImmortal)

    // Player status
    val healthStatus = formatHealthStatus(healthPoints, isBlessed)
    printPlayerStatus(auraColor, isBlessed, name, healthStatus)

    castFireball(5)
}


//右鍵 Refactor -> Funtion 生成 (cmd + alt + M / ctrl + alt + M)

private fun auraColor(isBlessed: Boolean, healthPoints: Int, isImmortal: Boolean) =
        when (isBlessed && healthPoints > 50 || isImmortal) {
            true -> "GREEN"
            else -> "NONE"
        }

private fun formatHealthStatus(healthPoints: Int, isBlessed: Boolean) =
        when (healthPoints) {
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

private fun printPlayerStatus(auraColor: String, isBlessed: Boolean, name: String, healthStatus: String) {
    println("(Aura: $auraColor) " +
            "(Blessed: ${if (isBlessed) "YES" else "NO"})")
    println("$name $healthStatus")
}

private fun castFireball(numFireballs: Int = 2) {
    val damage = when (numFireballs) {
        in 0..10 -> numFireballs * 5
        else -> 0
    }

    val status = when (damage) {
        in 1..10 -> "tipsy"
        in 11..20 -> "sloshed"
        in 21..30 -> "soused"
        in 31..40 -> "stewed"
        in 41..50 -> "..t0aSt3d"
        else -> "Unknown"
    }
    println("damage: $damage, status: $status")
}