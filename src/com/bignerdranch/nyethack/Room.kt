package com.bignerdranch.nyethack

open class Room(val name:String){

    var monster:Monster?=Goblin()
    protected open val dangerLevel=5
    fun description()="Room: $name\n"+
            "Danger Level: $dangerLevel"+
            "\nCreature: ${monster?.description ?: "none"}"
    open fun load()="Nothing much to see here..."
}