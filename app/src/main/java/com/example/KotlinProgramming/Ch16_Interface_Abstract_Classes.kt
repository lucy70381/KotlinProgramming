package com.example.KotlinProgramming

import android.accounts.AuthenticatorDescription
import java.io.File
import java.lang.Exception
import java.lang.IllegalStateException
import java.util.*
import kotlin.system.exitProcess

//Defining an interface
interface Fightable {
    var healthPoints: Int
    val diceCount: Int
    val diceSides: Int

    //Default Implementations
    val damageRoll: Int
        get() = (0 until diceCount).map {
            Random().nextInt(diceSides + 1)
        }.sum()

    fun attack(opponent: Fightable): Int
}

//Defining an abstract class
abstract class Monster(val name: String, val description: String, override var healthPoints: Int) : Fightable {
    override fun attack(opponent: Fightable): Int {
        val damageDealt = damageRoll
        opponent.healthPoints -= damageDealt
        return damageDealt
    }
}

//Subclassing an abstract class
class Goblin(name: String = "Goblin", description: String = "A nasty-looking goblin", healthPoints: Int = 30)
    : Monster(name, description, healthPoints) {

    //Implementing properties in the subclass of an abstract class
    override val diceCount: Int = 2
    override val diceSides: Int = 8

}

open class Room16(val name: String) {
    protected open val dangerLevel = 5

    //Adding a monster to each room
    var monster: Monster? = Goblin()

    fun description() = "Room: $name\n" +
            "Danger level: $dangerLevel\n" +
            "Creature: ${monster?.description ?: "none."}"

    open fun load() = "Nothing much to see here..."
}

private object Game16 {
    private val player = Player16("Madrigal")
    private var currentRoom: Room16 = TownSquare16()
    private var worldMap = listOf(
        listOf(currentRoom, Room16("Tavern"), Room16("Back Room")),
        listOf(Room16("Long Corridor"), Room16("Generic Room"))
    )

    init {
        println("Welcome, adventurer")
        player.castFireball()
    }

    fun play() {
        while (true) {
            println(currentRoom.description())
//            println(currentRoom.load())
            printPlayerStatus(player)

            print("> Enter your command: ")
            println("Last command: ${GameInput(readLine()).processCommand()}")
        }
    }

    private fun printPlayerStatus(player: Player16) {
        println("(Aura: ${player.auraColor()}) " +
                "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
        println("${player.name} ${player.formatHealthStatus()}")
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

    //Defining the fight function
    private fun fight() = currentRoom.monster?.let {
        while(player.healthPoints > 0 && it.healthPoints > 0) {
            //Calling the slay function
            slay(it)
            Thread.sleep(1000)
        }

        "Combat complete."
    } ?: "There's nothing here to fight."

    //Defining the slay function
    private fun slay(monster: Monster) {
        println("${monster.name} did ${monster.attack(player)} damage!")
        println("${player.name} did ${player.attack(monster)} damage!")

        if (player.healthPoints <= 0) {
            println(">>>> You have been defeated! Thanks for playing. <<<<")
            exitProcess(0)
        }

        if (monster.healthPoints <= 0) {
            println(">>>> ${monster.name} has been defeated! <<<<")
            currentRoom.monster = null
        }
    }

    private class GameInput(args: String?) {
        private val input = args ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, { "" })

        //Defining a function in a nested class
        fun processCommand() = when (command.toLowerCase()) {
            "fight" -> fight()
            "move" -> move(argument)
            else -> commandNotFound()
        }
        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"
    }
}

open class TownSquare16: Room16("Town Square") {
    //Overriding dangerLevel
    override val dangerLevel = super.dangerLevel - 3

    private var bellSound = "GWONG"

    //final keyword allows you to specify that a function cannot be overridden
    //any subclass of TownSquare could provide an overriding function for description but not load
    final override fun load() = "The villagers rally and cheer as you enter!\n${ringBell()}"

    fun ringBell() = "The bell tower announces your arrival. $bellSound"
}


//Implementing an interface (override var healthPoints / : Fightable)
//驚嘆號 -> Implement members
class Player16(_name: String, override var healthPoints: Int = 100, var isBlessed: Boolean = false, private var isImmortal: Boolean) : Fightable {

    //Implementing Fightable members
    //Stubbing out an interface implementation
    override val diceCount/*: Int*/ = 3

    override val diceSides/*: Int*/ = 6

    override fun attack(opponent: Fightable): Int {
        val damageDealt = if (isBlessed) {
            damageRoll * 2
        } else {
            damageRoll
        }
        opponent.healthPoints -= damageDealt
        return damageDealt
    }

    lateinit var alignment: String


    var name = _name
        get() = "${field.capitalize()} of Tampa"
        private set(value) {
            field = value.trim()
        }

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

fun main(args: Array<String>) {
    Game16.play()
}