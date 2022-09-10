package com.example.KotlinProgramming

//Making the Room class open for subclassing -> open
open class Room(val name: String) {
    //Declaring a protected property
    protected open val dangerLevel = 5

    fun description() = "Room: $name\n" +
            "Danger level: $dangerLevel"

    //Declaring an open function
    open fun load() = "Nothing much to see here..."
}

//Declaring the TownSquare class
open class TownSquare: Room("Town Square") {
    //Overriding dangerLevel
    override val dangerLevel = super.dangerLevel - 3

    private var bellSound = "GWONG"

    //final keyword allows you to specify that a function cannot be overridden
    //any subclass of TownSquare could provide an overriding function for description but not load
    final override fun load() = "The villagers rally and cheer as you enter!\n${ringBell()}"

    private fun ringBell() = "The bell tower announces your arrival. $bellSound"
}

fun main(args: Array<String>) {
    val player = Player13("Madrigal")
    player.castFireball()
    println()

    //Printing the room description
    var currentRoom = Room("Foyer")
    println(currentRoom.description())
    println(currentRoom.load())
    println()

    //Calling subclass function implementation
    var currentRoom2: Room = TownSquare()
    println(currentRoom2.description())
    println(currentRoom2.load())
    printPlayerStatus(player)
    println()

    var room = Room("Foyer")
    println(room is TownSquare)

    var townSquare = TownSquare()
    println(townSquare is Room)
}


private fun printPlayerStatus(player: Player13) {
    println("(Aura: ${player.auraColor()}) " +
            "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
    println("${player.name} ${player.formatHealthStatus()}")
}

fun printIsSourceOfBlessings(any: Any) {
    val isSourceOfBlessings = if (any is Player) {
        any.isBlessed
    } else {
        (any as Room).name == "Fount of Blessings"
    }
    println("$any is a source of blessings: $isSourceOfBlessings")
}