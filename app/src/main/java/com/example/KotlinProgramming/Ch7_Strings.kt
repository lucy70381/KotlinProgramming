package com.example.KotlinProgramming

const val TAVERN_NAME = "Taernyl's Folly"

fun main(args: Array<String>) {
    placeOrder("shandy,Dragon's Breath,5.91")
    showSymbol()
}

private fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order.")

    //val data = menuData.split(',')
    //val type = data[0]
    //val name = data[1]
    //val price = data[2]

    //Destructuring assignment - 解構賦值
    val (type, name, price) = menuData.split(',')
    val message = "Madrigal buys a $name ($type) for $price."
    println(message)

    // replace 替換字元
    val phrase = "Ah, delicious $name!"
    println(toDragonSpeak(phrase))
}

private fun toDragonSpeak(phrase: String) =
        phrase.replace(Regex("[aeiou]")) {
            when(it.value) {
                "a" -> "4"
                "e" -> "3"
                "i" -> "1"
                "o" -> "0"
                "u" -> "|_|"
                else -> it.value
            }
        }

private fun showSymbol() {
    val omSymbol = '\u0950'
    print(omSymbol)
}


/*
Escape sequence Meaning
       \t       Tab character
       \b       Backspace character
       \n       Newline character
       \r       Carriage return
       \"       Double quotation mark
       \'       Single quotation mark/apostrophe Backslash
       \\       Single quotation mark/apostrophe Backslash
       \$       Dollar sign
       \u       Unicode character
*/
