package com.example.KotlinProgramming

//18.8 Adding an extension property
val String.numVowels
    get() = count { "aeiouy".contains(it) }


//18.1 Adding an extension to String
fun String.addEnthusiasm(amount: Int = 1) = this + "!".repeat(amount)

//18.3 Extending Any
//fun Any.easyPrint() = println(this)

//18.5 Making easyPrint chainable
//fun Any.easyPrint(): Any
//18.7 Making easyPrint generic{
fun <T> T.easyPrint(): T {
    println(this)
    return this
}

//18.10 Adding an extension on a nullable type
infix fun String?.printWithDefault(default: String) = print(this ?: default)

//18.11 Adding a private random extension
private fun <T> Iterable<T>.random(): T = this.shuffled().first()

fun main(args: Array<String>) {
    //18.2 Calling the new extension on a String receiver instance
    //"Madrigal has left the building".addEnthusiasm().easyPrint()

    //18.6 Calling easyPrint twice
    "Madrigal has left the building".easyPrint().addEnthusiasm().easyPrint()

    //18.4 easyPrint is available on all subtypes
    42.easyPrint()

    //18.9 Using an extension property
    "How many vowels?".numVowels.easyPrint()

    //18.10 Adding an extension on a nullable type
    val nullableString: String? = null
    nullableString printWithDefault "Default string"                // With infix
    //nullableString.printWithDefault("Default string")             // Without infix

    //18.12 Using the random extension (Ch10)
    val patronList = listOf("Eli", "Mordoc", "Sophie")
    val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
    (0..9).forEach {
        val first = patronList.random()
        val last = lastName.random()
        val name = "$first $last"
    }
}

