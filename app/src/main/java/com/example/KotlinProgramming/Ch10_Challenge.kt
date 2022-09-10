package com.example.KotlinProgramming

import java.io.File

fun main(args: Array<String>) {
    //Formatted Tavern Menu
    val menuList = File(System.getProperty("user.dir") + "/data/file.txt")
            .readText()
            .split("\n")

    println("*** Welcome to Taernyl's Folly ***\n")
    menuList.forEach {
        var (_, name, price) = it.split(',')
        name = changeName(name)
        val length = name.length + price.length
        println("$name${".".repeat(34 - length)}$price")
    }

    //Advanced Formatted Tavern Menu
    var index = 0
    var menuType = listOf<String>()
    println("*** Welcome to Taernyl's Folly ***")
    menuList.forEach {
        var (type, name, price) = it.split(',')
        index++
        if (!menuType.contains(type)) {
            menuType += type
            println("${" ".repeat((30 - type.length) / 2)}~[$type]~")
            name = changeName(name)
            val length = name.length + price.length
            println("$name${".".repeat(34 - length)}$price")

            for (x in index until menuList.size) {
                var (type2, name2, price2) = menuList[x].split(',')
                if (type == type2) {
                    name2 = changeName(name2)
                    val length2 = name2.length + price2.length
                    println("$name2${".".repeat(34 - length2)}$price2")
                }
            }

        }
    }
}

val changeName: (String) -> String = {
    when (it) {
        "shirley temple" -> "Shirley's Temple"
        "goblet of la croix" -> "Goblet of LaCroix"
        "pickled camel hump" -> "Pickled Camel Hump"
        "iced boilermaker" -> "Iced Boilermaker"
        else -> it
    }
}