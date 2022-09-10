package com.example.KotlinProgramming

fun main(args: Array<String>) {

    //Anonymous Functions
    val numLetters = "Mississippi".count { letter -> letter == 's' }
    println(numLetters)

    println({
        val currentYear = 2018
        "Welcome to SimVillage, Mayor! (copyright $currentYear)\n"
    }())

    //Type Inference Support
    val greetingFunction = {
        val currentYear = 2018
        "Welcome to SimVillage, Mayor! (copyright $currentYear)\n"
    }
    println(greetingFunction())

    //The "it" keyword (only one parameter)
    val greetingFunction2: (String) -> String = {
        val currentYear = 2018
        "Welcome to SimVillage, $it! (copyright $currentYear)\n"
    }

    //Function arguments
    //val greetingFunction: (String, Int) -> String = { playerName, numBuildings ->
    val greetingFunction3 = { playerName: String, numBuildings: Int ->  //Type Inference Support
        val currentYear = 2018
        println("Adding $numBuildings houses")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)\n"
    }
    println(greetingFunction3("Guyal", 2))

    //Function References ::
    runSimulation("Run", ::printConstructionCost) { playerName, numBuildings ->
        val currentYear = 2018
        println("Adding $numBuildings houses")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)\n"
    }

    runSimulation2()

}

//Defining a Function That Accepts a Function
//Function Inlining (使用 inline 能避免函式的 lambda 形參額外建立 Function 物件, 減少函式呼叫造成的額外開銷)
inline fun runSimulation(playerName: String,
                         costPrinter: (Int) -> Unit,
                         greetingFunction: (String, Int) -> String) {
    val numBuildings = (1..3).shuffled().last()
    costPrinter(numBuildings)
    println(greetingFunction(playerName, numBuildings))
}

fun printConstructionCost(numBuildings: Int) {
    val cost = 500
    println("construction cost: ${cost * numBuildings}")
}

fun runSimulation2() {
    val greetingFunction3: (String) -> String = configureGreetingFunction()
    println(greetingFunction3("Jerry"))
}

//Function Type as Return Type
fun configureGreetingFunction(): (String) -> String {
    val structureType = "hospitals"
    var numBuildings = 5
    return { playerName: String ->
        val currentYear = 2018
        numBuildings += 1
        println("Adding $numBuildings $structureType")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)\n"
    }
}