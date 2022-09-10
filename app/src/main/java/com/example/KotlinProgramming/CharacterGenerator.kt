package com.example.KotlinProgramming

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.Serializable
import java.net.URL

private const val CHARACTER_DATA_URL = "https://chargen-api.herokuapp.com/"

private fun <T> List<T>.rand() = shuffled().first()
private fun Int.roll() = (0 until this)
        .map { (1..6).toList().rand() }
        .sum()
        .toString()

private val firstName = listOf("Eli", "Alex", "Sophie")
private val lastName = listOf("Lightweaver", "Greatfoot", "Oakenfeld")

object CharacterGenerator {
    // 21.8 Making the CharacterData class Serializable (CharacterGenerator.kt)
    data class CharacterData(val name: String, val race: String, val dex: String,
                             val wis: String, val str: String) : Serializable

    private fun name() = "${firstName.rand()} ${lastName.rand()}"

    private fun race() = listOf("dwarf", "elf", "halfling").rand()

    private fun dex() = 4.roll()

    private fun wis() = 3.roll()

    private fun str() = 5.roll()

    fun generate() = CharacterData(name(), race(), dex(), wis(), str())

    // 22.1 Adding the fromApiData function
    fun fromApiData(apiData: String): CharacterData {
        val (rice, name, dex, wis, str) = apiData.split(',')
        return CharacterData(name, rice, dex, wis, str)
    }

    /* 22.4 Adding the fetchCharacterData function -> App crash
    fun fetchCharacterData(): CharacterData {
        val apiData = URL(CHARACTER_DATA_URL).readText()
        return fromApiData(apiData)
    }*/

    // 22.6 需要 Enabling coroutines (app/build.gradle)
    //Challenge: Minimum Strength
    fun fetchCharacterData(): Deferred<CharacterData> {
        lateinit var apiData: String
        return GlobalScope.async {

            while (true) {
                apiData = URL(CHARACTER_DATA_URL).readText()
                if (fromApiData(apiData).str.toInt() > 10) break
            }
            fromApiData(apiData)
        }
    }
}