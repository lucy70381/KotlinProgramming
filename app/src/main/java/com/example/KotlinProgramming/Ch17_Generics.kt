package com.example.KotlinProgramming

//17.1 Creating a generic type
//17.9 Constraining the generic parameter to Loot only
//17.10 Adding vararg (multiple items)
class LootBox<T : Loot>(vararg item: T) {
    var open = false
    /*
    private var loot: T = item

    //17.3 Adding a fetch function
    fun fetch(): T? {
        return loot.takeIf { open }
    }

    //17.6 Using multiple generic type parameters
    fun <R> fetch(lootModFunction: (T) -> R): R? {
        return lootModFunction(loot).takeIf { open }
    }
    */

    //17.11 Indexing into the loot array
    private var loot: Array<out T> = item

    fun fetch(item: Int): T? {
        return loot[item].takeIf { open }
    }

    fun <R> fetch(item: Int, lootModFunction: (T) -> R): R? {
        return lootModFunction(loot[item]).takeIf { open }
    }

    //17.13 Adding a get operator to LootBox
    operator fun get(index: Int): T? = loot[index].takeIf { open }
}

//17.8 Adding a superclass
open class Loot(val value: Int)

//17.2 Defining loot boxes (Fedora, Coin)
class Fedora(val name: String, value: Int): Loot(value)

class Coin(value: Int): Loot(value)

//17.15 Defining Barrel
//class Barrel<T>(var item: T)

//17.20 Adding out (子類泛型對象可以賦值給父類泛型對象)
//class Barrel<out T>(val item: T)

//17.22 Marking Barrel with in (父類泛型對象可以賦值給子類泛型對象)
class Barrel<in T>(item: T)

fun main(args: Array<String>) {
    //17.16 Defining Barrels in main
    var fedoraBarrel: Barrel<Fedora> = Barrel(Fedora("a generic-looking", 15))
    var lootBarrel: Barrel<Loot> = Barrel(Coin(15))

    /*
    //17.17 Attempting to reassign lootBarrel
    lootBarrel = fedoraBarrel

    //17.18 Assigning a coin to lootBarrel.item
    //lootBarrel.item = Coin(15)

    //17.19 Accessing fedoraBarrel.item
    //val myFedora: Fedora = fedoraBarrel.item
    //17.21 Changing the assignment
    val myFedora: Fedora = lootBarrel.item
    */

    //17.23 Reversing the assignment
    fedoraBarrel = lootBarrel

    //17.12 Testing the new LootBox
    //val lootBoxOne: LootBox<Fedora> = LootBox(Fedora("a generic-looking", 15))
    val lootBoxOne: LootBox<Fedora> = LootBox(Fedora("a generic-looking", 15),
            Fedora("a dazzling magenta fedora", 25))
    val lootBoxTwo: LootBox<Coin> = LootBox(Coin(15))

    //17.5 Opening the box
    lootBoxOne.open = true

    /*
    //17.4 Testing the generic fetch function
    lootBoxOne.fetch()?.run {
        println("You retrieve $name from the box!")
    }

    //17.7 Passing the loot-modification function as an argument
    val coin = lootBoxOne.fetch() {
        Coin(it.value * 3)
    }
    */

    //17.12 Testing the new LootBox
    lootBoxOne.fetch(1)?.run {
        println("You retrieve $name from the box!")
    }

    val coin = lootBoxOne.fetch(0) {
        Coin(it.value * 3)
    }
    coin?.let { println(it.value) }

    //17.14 Using get
    val fedora = lootBoxOne[1]
    fedora?.let { println(it.name) }
}