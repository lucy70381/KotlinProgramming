package com.example.KotlinProgramming

import java.lang.Exception
import java.lang.IllegalStateException

fun main(args: Array<String>) {
    Game2.play()
}

object Game2 {
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
            "quit", "exit" -> goodbye()
            "map" -> map()
            "move" -> move(argument)
            "ring" -> ring()
            else -> commandNotFound()
        }
        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"
    }

    //Challenge: “Quit” Command
    private fun goodbye (): String {
        try {
            throw IllegalStateException()
        } catch (e: Exception) {
            return "Goodbye~"
        }
    }

    //Challenge: Implementing a World Map
    private fun map () {
        worldMap.forEach { list ->
            list.forEach { room15 ->
                print("${if (currentRoom.name == room15.name) "X" else "O" } ")
            }
            println()
        }
    }

    //Challenge: Ring the Bell
    private fun ring (): String {
        return when (currentRoom is TownSquare15) {
            true -> (currentRoom as TownSquare15).ringBell()
            else -> "It's not in town square."
        }
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