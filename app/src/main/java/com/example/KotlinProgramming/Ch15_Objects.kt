package com.example.KotlinProgramming

import java.io.File
import java.lang.Exception
import java.lang.IllegalStateException

class Player15(_name: String, var healthPoints: Int = 100, val isBlessed: Boolean, private val isImmortal: Boolean) {

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
    var currentPosition = Coordinate(0, 0)

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

open class Room15(val name: String) {
    //Declaring a protected property
    protected open val dangerLevel = 5

    fun description() = "Room: $name\n" +
            "Danger level: $dangerLevel"

    //Declaring an open function
    open fun load() = "Nothing much to see here..."
}

//Declaring the TownSquare class
open class TownSquare15: Room15("Town Square") {
    //Overriding dangerLevel
    override val dangerLevel = super.dangerLevel - 3

    private var bellSound = "GWONG"

    //final keyword allows you to specify that a function cannot be overridden
    //any subclass of TownSquare could provide an overriding function for description but not load
    final override fun load() = "The villagers rally and cheer as you enter!\n${ringBell()}"

    fun ringBell() = "The bell tower announces your arrival. $bellSound"
}

fun main(args: Array<String>) {
    //Calling a function defined on an object declaration
    println(Robot.Color.color)
    println(Robot.color)

    //1.data class會override toString()
    var currentPosition = Coordinate(1, 0)
    println(currentPosition)    //Coordinate(x=0, y=0)

    //2.data class新增了componentN的方法，而N取決於這個class有多少個屬性
    println(currentPosition.component1())   //1
    println(currentPosition.component2())   //0
    val (x, y) = currentPosition
    println("$x, $y")

    //3.data class新增了copy()方法
    //只要在copy()方法中指定屬性與要修改的數值，就可以快速產生一個同樣結構的class
    val clonedDataClass = currentPosition.copy(y = 30)
    println(clonedDataClass.y)

    println(Coordinate(1, 0) == Coordinate(1, 0))

    Direction.EAST.updateCoordinate(Coordinate(1,0))

    Game.play()
}

//Defining an enum constructor
enum class Direction(private val coordinate: Coordinate) {
    NORTH(Coordinate(0, -1)),
    EAST(Coordinate(1, 0)),
    SOUTH(Coordinate(0, 1)),
    WEST(Coordinate(-1, 0));

    //Defining a function in an enum
    fun updateCoordinate(playerCoordinate: Coordinate) =
            //Coordinate(playerCoordinate.x + coordinate.x, playerCoordinate.y + coordinate.y)
            coordinate + playerCoordinate
}

//data class
data class Coordinate(val x: Int, val y: Int) {
    val isInBounds = x >= 0 && y >= 0

    //Using an overloaded operator
    operator fun plus(other: Coordinate) = Coordinate(x + other.x, y + other.y)
}

/*
Operator Function name  Purpose
+        plus           Adds an object to another.
+=       plusAssign     Adds an object to another and assigns the result to the first.
==       equals         Returns true if two objects are equal, false otherwise.
>        compareTo      Returns true if the object on the lefthand side is greater than the object on the righthand side, false otherwise.
[]       get            Returns the element in a collection at a given index.
..       rangeTo        Creates a range object.
in       contains       Returns true if an object exists within a collection.
*/


//Companion objects (伴生) = Java 的 static
class Robot{
    companion object Color{
        var color= "White"
    }
}

//Singleton (單例模式)
object Game {
    private val player = Player15("Madrigal")
    private var currentRoom: Room15 = TownSquare15()
    private var worldMap = listOf(
            listOf(currentRoom, Room15("Tavern"), Room15("Back Room")),
            listOf(Room15("Long Corridor"), Room15("Generic Room"))
    )

    //Adding an init block to Game
    init {
        println("Welcome, adventurer")
        player.castFireball()
    }

    fun play() {
        while (true) {
            println(currentRoom.description())
            println(currentRoom.load())
            printPlayerStatus(player)

            //Accepting user input
            print("> Enter your command: ")
            //Using GameInput
            println("Last command: ${GameInput(readLine()).processCommand()}")
        }
    }

    private fun printPlayerStatus(player: Player15) {
        println("(Aura: ${player.auraColor()}) " +
                "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
        println("${player.name} ${player.formatHealthStatus()}")
    }

    //Defining a nested class
    private class GameInput(args: String?) {
        private val input = args ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, { "" })

        //Defining a function in a nested class
        fun processCommand() = when (command.toLowerCase()) {
            "move" -> move(argument)
            else -> commandNotFound()
        }
        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"
    }

    private fun move (directionInput: String) {
        try {
            val direction = Direction.valueOf(directionInput.toUpperCase())
            val newPosition = direction.updateCoordinate(player.currentPosition)
            if(!newPosition.isInBounds) {
                throw IllegalStateException("$direction is out of bounds.")
            }
            val newRoom = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "OK, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"
        } catch (e: Exception) {
            "Invalid direction: $directionInput."
        }
    }
}