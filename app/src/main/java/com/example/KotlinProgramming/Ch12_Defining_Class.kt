package com.example.KotlinProgramming
class Player {
    var weapon: Weapon? = Weapon("Ebony Kris")
    fun printWeaponName() {
        weapon?.also {
            println(it.name)
        }
    }

    var name = "madrigal"
        get() = field.capitalize()
        private set(value) {
            field = value.trim()
        }

    var healthPoints = 89
    val isBlessed = true
    private val isImmortal = false

    fun castFireball(numFireballs: Int = 2) {
        println("A glass of Fireball springs into existence. (x$numFireballs)")
    }

    fun auraColor() =
            when (isBlessed && healthPoints > 50 || isImmortal) {
                true -> "GREEN"
                else -> "NONE"
            }

    fun formatHealthStatus() =
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
}

class Weapon(val name: String)

class Dice() {
    val rolledValue
        get() = (1..6).shuffled().first()
}

fun main(args: Array<String>) {

    val player = Player()
    player.castFireball(5)
    printPlayerStatus(player)
    player.printWeaponName()

    val myD6 = Dice()
    println(myD6.rolledValue)
    println(myD6.rolledValue)
    println(myD6.rolledValue)
}

private fun printPlayerStatus(player: Player) {
    println("(Aura: ${player.auraColor()}) " +
            "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
    println("${player.name} ${player.formatHealthStatus()}")
}