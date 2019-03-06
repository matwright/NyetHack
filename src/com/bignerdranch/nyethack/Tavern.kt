package com.bignerdranch.nyethack

import java.io.File

const val TAVERN_NAME="Taernyl's Folly"

var playerGold=10
var playerSilver=10
val patronList= listOf("Eli","Mordoc","Sophie")
val lastname = listOf("Ironfoot","Fernsworth","Baggins")
val menuList=File("data/tavern-menu-data.txt").readText().split("\n");
fun main() {

    val mapped:Map<String,Int> = mapOf<String,Int>("one" to 1,"two" to 2)
    println(mapped["one"])





    //placeOrder()
    println(patronList.random())

    menuList.forEachIndexed{ index, data-> println("$index : $data")}
}

private fun placeOrder(player:Player){
    val indexOfApostrophe= TAVERN_NAME.indexOf('\'')
    val tavernMaster= TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("${player.name} speaks with $tavernMaster about their order.")
}