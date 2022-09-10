package com.example.KotlinProgramming

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_new_character.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.android.Main
import kotlinx.coroutines.android.UI
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

// 21.7 Serializing the characterData
private const val CHARACTER_DATA_KEY = "CHARACTER_DATA_KEY"

// 21.10 Defining a characterData extension property
private var Bundle.characterData
    get() = getSerializable(CHARACTER_DATA_KEY) as CharacterGenerator.CharacterData
    set(value) = putSerializable(CHARACTER_DATA_KEY, value)

class NewCharacterActivity : AppCompatActivity() {
    private var characterData = CharacterGenerator.generate()

    // 21.7 Serializing the characterData
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //outState.putSerializable(CHARACTER_DATA_KEY, characterData)
        // 21.11 Using the new extension property
        outState.characterData = characterData
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_character)

        /* 21.9 Fetching the serialized character data
        characterData = savedInstanceState?.let {
            it.getSerializable(CHARACTER_DATA_KEY) as CharacterGenerator.CharacterData
        } ?: CharacterGenerator.generate()*/

        // 21.11 Using the new extension property 避免旋轉螢幕時資料重刷
        savedInstanceState?.let {
            characterData = it.characterData
            displayCharacterData()
        } ?: fetchDataFromWeb()

        /*  21.5 Refactoring to displayCharacterData
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val raceTextView = findViewById<TextView>(R.id.raceTextView)
        val dexterityTextView = findViewById<TextView>(R.id.dexterityTextView)
        val wisdomTextView = findViewById<TextView>(R.id.wisdomTextView)
        val strengthTextView = findViewById<TextView>(R.id.strengthTextView)
        val generateButton = findViewById<Button>(R.id.generateButton)

        nameTextView.text = characterData.name
        raceTextView.text = characterData.race
        dexterityTextView.text = characterData.dex
        wisdomTextView.text = characterData.wis
        strengthTextView.text = characterData.str*/


        // 21.6 Setting a click listener
        generateButton.setOnClickListener {
            //characterData = CharacterGenerator.generate()

            /* 22.2 Testing the fromApiData function
            characterData = CharacterGenerator.fetchCharacterData()
            displayCharacterData()*/


            // 22.8 Awaiting the API results
            fetchDataFromWeb()
        }
    }

    //Challenge: Live Data
    private fun displayCharacterData() {
        characterData.run { 
            nameTextView.text = name
            raceTextView.text = race
            dexterityTextView.text = dex
            wisdomTextView.text = wis
            strengthTextView.text = str
        }
    }

    private fun fetchDataFromWeb() {
        GlobalScope.launch(Dispatchers.Main){
            characterData = CharacterGenerator.fetchCharacterData().await()
            displayCharacterData()
        }
    }
}