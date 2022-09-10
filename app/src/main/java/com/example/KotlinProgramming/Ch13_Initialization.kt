package com.example.KotlinProgramming

import java.io.File

//Defining a primary constructor
//Defining a default argument in a constructor (healthPoints: Int = 100)
class Player13(_name: String, var healthPoints: Int = 100, val isBlessed: Boolean, private val isImmortal: Boolean) {

    //lateinit var 把初始化交給使用自己決定什麼時候初始化
    //lateinit只能用在var宣告，並且沒有getter / setter
    //var alignment: String = "Test"
    //var alignment: String? = null
    lateinit var alignment: String


    var name = _name
        get() = "${field.capitalize()} of hometowns"
        private set(value) {
            field = value.trim()
        }

    //lazy()功用類似lateinit，可以接受一個lambda，然後回傳一個lazy的instance
    //lazy的修飾必須是val，第一次被使用，初始化之後，就不允許內容再被修改
    val hometown by lazy { selectHometown() }

    //Defining an initializer block
    init {
        require(healthPoints > 0, { "healthPoints must be greater than zero." })
        require(name.isNotBlank(), { "Player must have a name." })
    }

    //Defining a secondary constructor
    constructor(name: String): this(name, isBlessed = true, isImmortal = false) {
        //Adding logic to a secondary constructor
        if (name.toLowerCase() == "kar") healthPoints = 40
    }

    private fun selectHometown() = File(System.getProperty("user.dir") + "/data/towns.txt")
            .readText()
            .split('\n')
            .shuffled()
            .first()

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

fun main(args: Array<String>) {
    //Named arguments
    val player = Player13("Madrigal")
    val player2 = Player13("Madrigal", healthPoints = 100, isBlessed = true, isImmortal = false)
}
