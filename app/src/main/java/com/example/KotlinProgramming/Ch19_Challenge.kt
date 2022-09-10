package com.example.KotlinProgramming


fun main(args: Array<String>) {
    //1.Reversing the Values in a Map
    val gradesByStudent = mapOf("Josh" to 4.0, "Alex" to 2.0, "Jane" to 3.0)
    println(gradesByStudent)
    println(flipValues(gradesByStudent))

    val patronList = listOf("Eli", "Mordoc", "Sophie")
    val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")

    //2.Applying Functional Programming to Tavern.kt
    //(0..9).forEach {
    //    val first = patronList.random()
    //    val last = lastName.random()
    //    val name = "$first $last"
    //    uniquePatrons += name
    //}

    val uniquePatrons: Set<String> = generateSequence {
        val first = patronList.random()
        val last = lastName.random()
        "$first $last"
    }.take(10).toSet()
    println(uniquePatrons)

    //uniquePatrons.forEach{
    //    patronGold[it] = 6.0
    //}
    val patronGold = uniquePatrons.associateWith { 6.0 }
    println(patronGold)

    //3.Sliding Window
    val valuesToAdd = listOf(1, 18, 73, 3, 44, 6, 1, 33, 2, 22, 5, 7)
    val res = valuesToAdd.filter { it >= 5 }.chunked(2) {
        it[0] * it[1]
    }.sum()
    println(res)
}

//1.Reversing the Values in a Map
fun flipValues(gradesByStudent: Map<String, Double>): Map<Double, String> {
    return gradesByStudent.map {
        it.value to it.key
    }.toMap()
}

