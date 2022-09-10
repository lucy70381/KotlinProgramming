package com.example.KotlinProgramming

import kotlin.system.measureNanoTime


fun main(args: Array<String>) {
    //19.1 Converting a list of animals to babies – with tails
    val animals = listOf("zebra", "giraffe", "elephant", "rat")
    val babies = animals
            .map{ animal -> "A baby $animal"}
            .map{ baby -> "$baby, with the cutest little tail ever!"}
    println(babies)

    //19.2 Original collection not modified
    print(animals)

    //19.3 Before and after mapping: same number of items, different types
    val tenDollarWords = listOf("auspicious", "avuncular", "obviate")
    val tenDollarWordLengths = tenDollarWords.map { it.length }
    println(tenDollarWordLengths)

    //19.4 Flattening two lists
    val list = listOf(listOf(1, 2, 3), listOf(4, 5, 6)).flatMap { it }
    println(list)

    //19.5 Filtering and flattening
    val itemsOfManyColors = listOf(listOf("red apple", "green apple", "blue apple"),
        listOf("red fish", "blue fish"), listOf("yellow banana", "teal banana"))
    val redItems = itemsOfManyColors.flatMap { it.filter { it.contains("red") } }
    println(redItems)

    //19.6 Filtering non-prime numbers
    val numbers = listOf(7, 4, 8, 4, 3, 22, 18, 11)
    val primes = numbers.filter { number ->
        (2 until number).map { number % it }
                .none { it == 0 }   //none 如果集合中沒有符合條件的元素，返回true，否則返回false
    }
    println(primes)

    //19.7 Combining two collections, functional style
    val employees = listOf("Denny", "Claudette", "Peter")
    val shirtSize = listOf("large", "x-large", "medium")
    val employeeShirtSizes = employees.zip(shirtSize).toMap()
    println(employeeShirtSizes)

    //fold 累加
    val foldedValue = listOf(1, 2, 3, 4).fold(0) { accumulator, number ->
        println("$accumulator $number")
        accumulator + (number * 3)
    }
    println("Final value: $foldedValue")

    //Sequences 建構序列
    val oddNumbers = generateSequence(1) { it + 2 } // `it` 是上一個元素
    println(oddNumbers.take(5).toList())

    val oneThousandPrimes = generateSequence(3) { value ->
        value + 1
    }.filter { it.isPrime() }.take(1000)
    println(oneThousandPrimes.toList())

    //Profiling
    val nanos = measureNanoTime {
        // List functional chain here
    }
    println(nanos)
}

fun Int.isPrime(): Boolean {
    (2 until this).map {
        if (this % it == 0) {
            return false // Not a prime!
        }
    }
    return true
}
