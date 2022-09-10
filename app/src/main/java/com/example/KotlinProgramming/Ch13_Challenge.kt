package com.example.KotlinProgramming

//Defining Sword
class Sword(_name: String) {
    var name = _name
        get() = "The Legendary $field"
        set(value) {
            field = value.toLowerCase().reversed().capitalize()
        }

    init {
        //Adding an initializer block
        //name = _name
    }
}

fun main(args: Array<String>) {
    //Referencing name
    val sword = Sword("Excalibur")
    println(sword.name)

    //Reassigning name
    sword.name = "Gleipnir"
    println(sword.name)

}