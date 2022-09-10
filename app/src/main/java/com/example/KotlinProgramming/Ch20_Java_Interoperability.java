package com.example.KotlinProgramming;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import kotlin.jvm.functions.Function1;

// 20.1 Declaring a class and method in Java
public class Ch20_Java_Interoperability {
    // 20.10 Declaring an int in Java
    // 20.14 Declaring a field in Java
    private int hitPoints = 52489112;
    public int getHitPoints() {
        return hitPoints;
    }

    //20.15 Exposing a greeting in Java
    private String greeting = "BLARGH";
    // 20.15 Exposing a greeting in Java
    public String getGreeting() {
        return this.greeting;
    }
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    // 20.9 Specifying that a return value will not be null
    @NotNull
    public String utterGreeting() {
        return greeting;
    }

    // 20.4 Returning null from a Java method
    // 20.6 Specifying that a return value will possibly be null
    @Nullable
    public String determineFriendshipLevel() {
        return null;
    }

    // 20.23 Only one method signature
    public void offerFood() {
        Hero.handOverFood("pizza");  // 若 Kotlin fun沒添加 @JvmOverloads 無法多載 -> Expected 2 arguments but found 1
    }

    // 20.34 Throwing an exception in Java
    public void extendHandInFriendship() throws Exception {
        throw new Exception();
    }

    // 20.38 Throwing an exception in Java
    // 若想要在 Java 中捕捉這個異常，必須於 Kotlin fun @Throws(Exception::class)
    public void apologize() {
        try {
            Hero.acceptApology();
        } catch (IOException e) {
            System.out.println("Caught!");
        }
    }

    // 20.18 Defining a main method in Java
    public static void main(String[] args) {
        // 20.19 Referencing a top-level Kotlin function from Java
        //System.out.println(Ch20_Java_InteroperabilityKt.makeProclamation());
        //20.21 Referencing a renamed top-level Kotlin function from Java
        System.out.println(Hero.makeProclamation());

        // 20.28 Accessing a Kotlin field directly in Java
        System.out.println("Spells: ");
        Spellbook spellbook = new Spellbook();
        for (String spell : spellbook.spells) {   // 若 Kotlin field沒添加 @JvmField 無法存取 -> 'spells' has private access
            System.out.println(spell);
        }

        // 20.30 Accessing a static value in Java
        // 若 Kotlin field沒添加 @JvmField 無法存取 -> 'MAX_SPELL_COUNT' has private access
        System.out.println("Max spell count: " + Spellbook.MAX_SPELL_COUNT);

        // 20.33 Invoking a static method in Java
        // 若 Kotlin companion object 內的fun沒添加 @JvmStatic 無法存取
        // -> Cannot resolve method 'getSpellbookGreeting' in 'Spellbook'
        Spellbook.getSpellbookGreeting();

        // 20.41 Storing a function type in a variable in Java
        Function1 translator = Hero.getTranslator();
        // 20.42 Calling a function type in Java
        translator.invoke("TRUCE");

    }
}
