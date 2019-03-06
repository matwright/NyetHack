package com.bignerdranch.nyethack

import sun.tools.jstat.Alignment
import java.io.File

class Player(_name:String,override var healthPoints: Int=100, var isBlessed:Boolean,private val isImmortal:Boolean) :Fightable{
    override val diceCount: Int
        get() = 3
    override val diceSides: Int
        get() = 6

    override fun attack(opponent: Fightable): Int {
        val damageDealt=if(isBlessed){
        damageRoll*2
        }else{
            damageRoll
        }
        opponent.healthPoints-=damageDealt
        return damageDealt
    }

    lateinit var alignment: String
    var currentPosition=Coordinate(0,0)
    val hometown by lazy{selectHomeTown()}
    private fun selectHomeTown()= File("data/towns.txt")  .readText().split("\n").shuffled().first()

    constructor(name: String):this(name,isBlessed=true,isImmortal=false)
    {
        if(name.toLowerCase()=="kar")healthPoints=40
    }

    override fun toString():String= this.name
    var name = _name
        get() = "${field.capitalize()} of $hometown";
        set(value) {
            field = value.trim().toLowerCase()
        }


    init {
        require(healthPoints>0,{"healthPoints must be greater than zero"})
        require(name.isNotBlank(),{"Player must have a name"})
    }


    fun castFireball(numFireBalls: Int = 2) =
        println("A glass of Fireball springs into existence. (x$numFireBalls)");

     fun auraColor() = if (isBlessed && healthPoints > 50 || isImmortal) "GREEN" else "NONE"


     fun formatHealthStatus() =
        when (healthPoints) {
            100 -> "is in excellent condition!"
            in 90..99 -> "has a few scratches"
            in 75..89 -> if (isBlessed) {
                "has some minor wounds, but is healing quite quickly!"
            } else {
                "has some minor wounds."
            }
            in 15..74 -> "looks pretty hurt."
            else -> "is in awful condition!"
        }


    fun proclaimFate(){
        if(::alignment.isInitialized) println(alignment)
    }
}
